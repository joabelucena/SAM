package br.com.ttrans.samapp.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.TaskDao;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.model.Task;
import br.com.ttrans.samapp.model.TaskCondition;

@Repository
public class TaskDaoImpl implements TaskDao {
	
	@Autowired
	private SessionFactory session;

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

		Boolean run;

		// Task is active, so proccess it
		if (task.getActive() == 1) {

			// Instantiate equipment iterator
			Iterator<Equipment> equipIt = task.getEquipments().iterator();

			// Creates criteria
			Criteria crit = session.getCurrentSession().createCriteria(Event.class);

			// Instantiate new objects
			TaskCondition condition;

			// Iterate equipments
			while (equipIt.hasNext()) {

				Iterator<TaskCondition> condIt = task.getItems().iterator();

				while (condIt.hasNext()) {
					
					crit.setProjection(Projections.rowCount());

					condition = condIt.next();
					
					switch (condition.getType()) {

					case AL:
						crit.add(Restrictions.eq("alarm", "aaa"));
						System.out.println("ALARME");
						break;
					case MT:

					default:
						System.out.println("QUALQUER OUTRA COISA");
					}

					crit.uniqueResult();

				}
			}
		}
	}

	@Override
	public void processAll() {
		
		//All Tasks
		List<Task> tasks = this.loadData();
		
		//Proccess each task
		for(int i = 0; i < tasks.size(); i++){
			this.proccess(tasks.get(i));
		}

	}

}
