package br.com.ttrans.samapp.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.CounterDao;
import br.com.ttrans.samapp.dao.EventDao;
import br.com.ttrans.samapp.dao.TaskDao;
import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.AlarmType;
import br.com.ttrans.samapp.model.Counter;
import br.com.ttrans.samapp.model.Counter.CounterId;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.model.Task;
import br.com.ttrans.samapp.model.TaskCondition;
import br.com.ttrans.samapp.service.TaskService;

@Repository
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private CounterDao counterDao;
	
	@Autowired
	private EventDao eventdao;
	
	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
	
	private static final String USR_TASK_INSERT = "SAM_TASK_MONITOR";

	@Transactional
	public void add(Task task, Authentication authentication) {
		
		Iterator<TaskCondition> conditions = task.getConditions().iterator();
		
		//Sets insert user and relational conditions
		while(conditions.hasNext()){
			TaskCondition cond = conditions.next();
			cond.setInsert(authentication.getName());
		}
		
		task.setInsert(authentication.getName());
		
		taskDao.add(task, authentication);

	}

	@Transactional
	public void edit(Task task, Authentication authentication) {
		
		Iterator<TaskCondition> conditions = task.getConditions().iterator();
		
		//Sets update user and relational conditions
		while(conditions.hasNext()){
			TaskCondition cond = conditions.next();
			
			//Eh registro novo
			if(cond.getInsert() == null){
				cond.setInsert(authentication.getName());				
			}else{
				cond.setUpdate(authentication.getName());
			}			
		}
		
		task.setUpdate(authentication.getName());
		
		taskDao.edit(task, authentication);
		
	}

	@Transactional
	public void delete(Task task, Authentication authentication) {
		taskDao.delete(task, authentication);
	}
	
	@Transactional
	public Task get(int id){
		return taskDao.get(id);
	}
	
	@Transactional
	public List<Task> loadData() {
		return taskDao.loadData();
	}
	
	@Transactional
	public void proccess(Task task) {
//		Boolean run = false;
//
//		// Task is active, so process it
//		if (task.getActive().equals("Y")) {
//
//			// Instantiate equipment iterator
//			Iterator<Equipment> equipIt = task.getEquipments().iterator();
//
//			// Creates criteria
//			// Criteria crit =
//			// session.getCurrentSession().createCriteria(Counter.class);
//
//			// Instantiate new objects
//			TaskCondition condition;
//			Equipment equipment;
//			Alarm alarm;
//
//			// Iteration on equipments
//			while (equipIt.hasNext()) {
//				
//				int counter = 0;
//
//				equipment = equipIt.next();
//
//				Iterator<TaskCondition> condIt = task.getConditions().iterator();
//				
//				// Iteration on conditions
//				while (condIt.hasNext()) {
//
//					condition = condIt.next();
//
//					switch (condition.getType()) {
//					
//					case "AL":
//
//						/********* Alarm *********/
//						alarm = new Alarm(condition.getField());
//
//						Counter ct = (Counter) counterDao.get(new CounterId(alarm, equipment));
//						
//						//Attributes counter value to counter variable
//						if (ct instanceof Counter) {
//							counter = ct.getCounter();
//						}
//
//						break;
//					case "MT":
//						/********* MTBF *********/
//						break;
//						
//					case "AT":
//						/********* Alarm Type *********/
//						
//						counter = counterDao.getCountByType(equipment, new AlarmType(Integer.parseInt(condition.getField())));
//
//					}
//					
//					//Do the rule
//					switch (condition.getRelOper()) {
//					
//					/**
//					 * if(<logic_operator> == AND){
//					 * 		run = run && counter <rel_operator> value
//					 * }else{
//					 * 		run = run || counter <rel_operator> value
//					 * }
//					 */
//					case ">":
//						run = condition.getLogicOper().equals("E") ? run && (counter > condition.getValue()) : run || (counter > condition.getValue());
//						break;
//					case "<":
//						run = condition.getLogicOper().equals("E") ? run && (counter < condition.getValue()) : run || (counter < condition.getValue());
//						break;
//					case "==":
//						run = condition.getLogicOper().equals("E") ? run && (counter == condition.getValue()) : run || (counter == condition.getValue());
//						break;
//					case ">=":
//						run = condition.getLogicOper().equals("E") ? run && (counter >= condition.getValue()) : run || (counter >= condition.getValue());
//						break;
//					case "<=":
//						run = condition.getLogicOper().equals("E") ? run && (counter <= condition.getValue()) : run || (counter <= condition.getValue());
//						break;
//					}
//					
//				} //<--- Conditions
//
//				if(run){
//					Event ev = new Event();
//					ev.setAlarm(task.getAlarm());
//					ev.setEquipment(equipment);
//					ev.setInsert(USR_TASK_INSERT);
//					ev.setDatetime(new Date());
//					
//					try {
//						eventdao.add(ev);
//					} catch (Exception e) {
//						logger.info("Erro na criação do evento para a regra: " + task.getId());
//						e.printStackTrace();
//					}
//				}
//			
//			}//<--- Equipments
//		}
//
	}

	@Transactional
	public void processAll() {
		
		//All Tasks
		List<Task> tasks = this.loadData();
		
		//Process each task
		for(int i = 0; i < tasks.size(); i++){
			
			// Process only active tasks
			if (tasks.get(i).getActive().equals("Y")) {
				this.proccess(tasks.get(i));
			}
			
		}
	}
}
