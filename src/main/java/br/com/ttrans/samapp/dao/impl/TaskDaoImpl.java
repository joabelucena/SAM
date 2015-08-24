package br.com.ttrans.samapp.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.TaskDao;
import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.Counter;
import br.com.ttrans.samapp.model.Counter.CounterId;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.model.Task;
import br.com.ttrans.samapp.model.TaskCondition;

@Repository
public class TaskDaoImpl implements TaskDao {
	
	@Autowired
	private SessionFactory session;
	
	private static final Logger logger = LoggerFactory.getLogger(TaskDaoImpl.class);

	@Override
	public void add(Task task, Authentication authentication) {
		task.setInsert(authentication.getName());
		session.getCurrentSession().save(task);
	}

	@Override
	public void edit(Task task, Authentication authentication) {
		task.setUpdate(authentication.getName());
		session.getCurrentSession().update(task);

	}

	@Override
	public void delete(Task task, Authentication authentication) {
		session.getCurrentSession().delete(task);

	}
	
	@Override
	public Task get(int id){
		return (Task) session.getCurrentSession().get(Task.class, id);
	}
	
	@Override
	public List<Task> loadData() {
		return session.getCurrentSession().createCriteria(Task.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
	
	@Override
	public void proccess(Task task) {

		Boolean run = false;

		// Task is active, so proccess it
		if (task.getActive().equals("Y")) {

			// Instantiate equipment iterator
			Iterator<Equipment> equipIt = null;//task.getEquipments().iterator();

			// Creates criteria
			// Criteria crit =
			// session.getCurrentSession().createCriteria(Counter.class);

			// Instantiate new objects
			TaskCondition condition;
			Equipment equipment;
			Alarm alarm;

			// Iterattion on equipments
			while (equipIt.hasNext()) {
				
				int counter = 0;

				equipment = equipIt.next();

				Iterator<TaskCondition> condIt = null;//task.getConditions().iterator();
				
				// Iterattion on conditions
				while (condIt.hasNext()) {

					condition = condIt.next();

					switch (condition.getType()) {
					
					case "AL":

						/********* Alarm *********/
						alarm = new Alarm(condition.getField());

						Counter ct = (Counter) session.getCurrentSession().get(
								Counter.class, new CounterId(alarm, equipment));
						
						//Attributes counter value to counter variable
						if (ct instanceof Counter) {
							counter = ct.getCounter();
						}

						break;
					case "MT":
						/********* MTBF *********/
						break;
						
					case "AT":
						/********* Alarm Type *********/
						
						String cQuery = "SELECT"
								+ " COALESCE(SUM(ACO_COUNTER),0) as COUNTER"
								+ " FROM ALARM_COUNTER"
								+ " LEFT JOIN ALARMS"
								+ " ON ACO_ALARM_ID = ALM_ID"
								+ " LEFT JOIN ALARMS_TYPE"
								+ " ON ALM_TYPE_ID = ATY_ID"
								+ " WHERE"
								+ " ACO_EQUIPMENT_ID = '" + equipment.getId() + "'"
								+ " AND ATY_ID = " + Integer.parseInt(condition.getField());
						
						SQLQuery query = session.getCurrentSession().createSQLQuery(cQuery)
								.addScalar("COUNTER", Hibernate.INTEGER);
						
						//Attributes counter value to counter variable
						counter = ((Integer) query.uniqueResult()).intValue();

					}
					
					//Do the rule
					switch (condition.getRelOper()) {
					
					/**
					 * if(<logic_operator> == AND){
					 * 		run = run && counter <rel_operator> value
					 * }else{
					 * 		run = run || counter <rel_operator> value
					 * }
					 */
					case ">":
						run = condition.getLogicOper().equals("E") ? run && (counter > condition.getValue()) : run || (counter > condition.getValue());
						break;
					case "<":
						run = condition.getLogicOper().equals("E") ? run && (counter < condition.getValue()) : run || (counter < condition.getValue());
						break;
					case "==":
						run = condition.getLogicOper().equals("E") ? run && (counter == condition.getValue()) : run || (counter == condition.getValue());
						break;
					case ">=":
						run = condition.getLogicOper().equals("E") ? run && (counter >= condition.getValue()) : run || (counter >= condition.getValue());
						break;
					case "<=":
						run = condition.getLogicOper().equals("E") ? run && (counter <= condition.getValue()) : run || (counter <= condition.getValue());
						break;
					}
					
				} //<--- Conditions

				//Abre o alarme para aquele equipamento
				if(run){
					Event ev = new Event();
					ev.setAlarm(task.getAlarm());
					ev.setEquipment(equipment);
					ev.setInsert("TASKMONITOR");
					ev.setDatetime(new Date());
					
					try {
						//Grava Evento
						session.getCurrentSession().save(ev);
					} catch (Exception e) {
						logger.info("Erro na criação do evento para a regra: " + task.getId());
						e.printStackTrace();
					}
				}
			
			}//<--- Equipments
		}
	}

	@Override
	public void processAll() {
		
		//All Tasks
		List<Task> tasks = this.loadData();
		
		//Proccess each task
		for(int i = 0; i < tasks.size(); i++){
			
			// Procces only active tasks
			if (tasks.get(i).getActive().equals("Y")) {
				this.proccess(tasks.get(i));
			}
		}

	}

}
