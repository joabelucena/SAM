package br.com.ttrans.samapp.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.TaskDao;
import br.com.ttrans.samapp.library.LogicOperator;
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
		return session.getCurrentSession().createCriteria(Task.class).list();
	}
	
	@Override
	public void proccess(Task task) {

		Boolean run = false;

		// Task is active, so proccess it
		if (task.getActive() == 1) {

			// Instantiate equipment iterator
			Iterator<Equipment> equipIt = task.getEquipments().iterator();

			// Creates criteria
			// Criteria crit =
			// session.getCurrentSession().createCriteria(Counter.class);

			// Instantiate new objects
			TaskCondition condition;
			Equipment equipment;
			Alarm alarm;

			// Iterattion on equipments
			while (equipIt.hasNext()) {

				equipment = equipIt.next();

				Iterator<TaskCondition> condIt = task.getItems().iterator();
				
				// Iterattion on conditions
				while (condIt.hasNext()) {

					condition = condIt.next();

					switch (condition.getType()) {
					
					case ALARM:

						/********* Alarme *********/
						alarm = new Alarm(condition.getField());

						Counter ct = (Counter) session.getCurrentSession().get(
								Counter.class, new CounterId(alarm, equipment));
						
						if (ct instanceof Counter) {
							switch (condition.getRelOper()) {
							
							case GREATER:
								run = condition.getLogicOper().equals(LogicOperator.AND) ? run && (ct.getCounter() > condition.getValue()) : run || (ct.getCounter() > condition.getValue());
								break;
							case LESS:
								run = condition.getLogicOper().equals(LogicOperator.AND) ? run && (ct.getCounter() < condition.getValue()) : run || (ct.getCounter() < condition.getValue());
								break;
							case EQUAL:
								run = condition.getLogicOper().equals(LogicOperator.AND) ? run && (ct.getCounter() == condition.getValue()) : run || (ct.getCounter() == condition.getValue());
								break;
							case GREATER_OR_EQUAL:
								run = condition.getLogicOper().equals(LogicOperator.AND) ? run && (ct.getCounter() >= condition.getValue()) : run || (ct.getCounter() >= condition.getValue());
								break;
							case LESS_OR_EQUAL:
								run = condition.getLogicOper().equals(LogicOperator.AND) ? run && (ct.getCounter() <= condition.getValue()) : run || (ct.getCounter() <= condition.getValue());
								break;
							}
						}

						break;
					case MTBF:
						/********* MTBF *********/
						
					case ALARM_TYPE:
						/********* Tipo de Alarme *********/
						
						String cQuery = "SELECT"
								+ " SUM(ACO_COUNTER)"
								+ " FROM ALARM_COUNTER"
								+ " LEFT JOIN ALARMS"
								+ " ON ACO_ALARM_ID = ALM_ID"
								+ " LEFT JOIN ALARMS_TYPE"
								+ " ON ALM_TYPE_ID = ATY_ID"
								+ " WHERE"
								+ " ACO_EQUIPMENT_ID = '" + equipment.getId() + "'"
								+ " AND ATY_ID = " + Integer.parseInt(condition.getField());
						
						SQLQuery qQuery = session.getCurrentSession().createSQLQuery(cQuery);
						
						System.out.println(qQuery.getQueryString());
						
						//Integer qt = (Integer) qQuery.uniqueResult().;
						Integer.parseInt((String) qQuery.uniqueResult());
						System.out.println();
						

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
			if (tasks.get(i).getActive() == 1) {
				this.proccess(tasks.get(i));
			}
		}

	}

}
