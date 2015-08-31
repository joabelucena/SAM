package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.TaskDao;
import br.com.ttrans.samapp.model.Task;

@Repository
public class TaskDaoImpl implements TaskDao {
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Task task, Authentication authentication) {
		session.getCurrentSession().persist(task);
	}

	@Override
	public void edit(Task task, Authentication authentication) {
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> loadData() {
		return session.getCurrentSession().createCriteria(Task.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
}
