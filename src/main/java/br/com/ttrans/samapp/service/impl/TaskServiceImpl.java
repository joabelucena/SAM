package br.com.ttrans.samapp.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import br.com.ttrans.samapp.dao.CounterDao;
import br.com.ttrans.samapp.dao.EventDao;
import br.com.ttrans.samapp.dao.TaskDao;
import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.AlarmType;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.model.Task;
import br.com.ttrans.samapp.model.TaskCondition;
import br.com.ttrans.samapp.model.TaskEquipment;
import br.com.ttrans.samapp.service.TaskService;
//import br.com.ttrans.samapp.model.Counter;
//import br.com.ttrans.samapp.model.Counter.CounterId;

@Repository
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private EventDao eventDao;
	
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
		
		// Process only active tasks
		if (task.getActive().equals("Y")) {
			
			//Retrieves a sorted collection of equipments
			List<TaskEquipment> equipments = new ArrayList<TaskEquipment>(task.getEquipments());
			
			Collections.sort(equipments, new Comparator<TaskEquipment>() {
			    public int compare(TaskEquipment o1, TaskEquipment o2) {
			        int v1 = o1.getId();
			        int v2 = o2.getId();
			        
			        return (v1 < v2 ? -1 : (v1 == v2 ? 0 : 1));
			    }
			});
			
			//Retrieves a sorted collection of conditions
			List<TaskCondition> conditions = new ArrayList<TaskCondition>(task.getConditions());
			
			Collections.sort(conditions, new Comparator<TaskCondition>() {
			    public int compare(TaskCondition o1, TaskCondition o2) {
			        String v1 = o1.getSeq();
			        String v2 = o2.getSeq();
			        
			        return (v1.compareTo(v2));
			    }
			});
			
			// Defines if task will be triggered 
			boolean run = true;

			
			// Iteration on equipments
			for(TaskEquipment taskEquip : equipments){
				
				// Defines counter
				int counter = 0;
				
				// Iteration on conditions
				for(TaskCondition condition : conditions){
					
					/********************************* Counter Selection *********************************/
					switch (condition.getType()) {
					
					case "AL":
						
						/********* Alarm *********/
						
						//Contador
						counter = eventDao.countByAlarm(  taskEquip.getEquipment()
														, new Alarm(condition.getField())
														, taskEquip.getProccess());
						
						break;
					case "MT":
						/********* MTBF *********/
						break;
						
					case "AT":
						
						/********* Alarm Type *********/
						
						//Contador
						counter = eventDao.countByType(  taskEquip.getEquipment()
														, new AlarmType( Integer.parseInt(condition.getField()))
														, taskEquip.getProccess());
						
					}
					/********************************* End of Counter Selection *********************************/
					
					
					/********************************* Rule Validation *********************************/
					
					if(condition.getLogicOper().equals("-")){
						//First sentence of conditions
						
						switch (condition.getRelOper()) {
						
						case ">":
							run = (counter > condition.getValue());
							break;
						case "<":
							run = (counter < condition.getValue());
							break;
						case "==":
							run = (counter == condition.getValue());
							break;
						case ">=":
							run = (counter >= condition.getValue());
							break;
						case "<=":
							run = (counter <= condition.getValue());
							break;
						default:
							/**
							 * MTBF Default treatment
							 */
							
						}
						
					}else{
						
						switch (condition.getRelOper()) {
						
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
						default:
							/**
							 * MTBF Default treatment
							 */
							
						}						
					}
					
					/********************************* End of Rule Validation *********************************/
					
				} //<--- Conditions
				
				
				if(run){
					
					Event ev = new Event();
					ev.setAlarm(task.getAlarm());
					ev.setEquipment(taskEquip.getEquipment());
					ev.setInsert(USR_TASK_INSERT);
					ev.setDatetime(new Date());
					
					try {
						
						//Add new event if rules were 'true'
						eventDao.add(ev);
						
						try {
							
							//Update last process date
							taskEquip.setProccess(new Date());
							
							taskDao.edit(task);
						} catch (Exception e){
							logger.info("Regra disparada, porém houveram erros na atualização da data de processamento. Regra: " + task.getId());
							e.printStackTrace();
						}
						
					} catch (Exception e) {
						logger.info("Erro na criação do evento para a regra: " + task.getId());
						e.printStackTrace();
					}
				}
			
			}//<--- Equipments
		}

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
