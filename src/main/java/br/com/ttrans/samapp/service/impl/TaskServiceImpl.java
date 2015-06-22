package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.TaskDao;
import br.com.ttrans.samapp.model.Task;
import br.com.ttrans.samapp.service.TaskService;

@Repository
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskDao dao;

	@Transactional
	public void add(Task task, Authentication authentication) {
		dao.add(task, authentication);

	}

	@Transactional
	public void edit(Task task, Authentication authentication) {
		dao.edit(task, authentication);

	}

	@Transactional
	public void delete(Task task, Authentication authentication) {
		dao.delete(task, authentication);

	}
	
	@Transactional
	public Task get(int id){
		return dao.get(id);
	}

	@Transactional
	public List<Task> loadData() {
		return dao.loadData();
	}

	@Transactional
	public void proccess(Task task) {
		dao.proccess(task);

	}

	@Transactional
	public void processAll() {
		dao.processAll();

	}

}
