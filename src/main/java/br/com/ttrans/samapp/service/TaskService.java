package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Task;

public interface TaskService {
	public void add(Task task, Authentication authentication);
	public void edit(Task task, Authentication authentication);
	public void delete(Task task, Authentication authentication);
	public Task get(int id);
	public List<Task> loadData();
	public void proccess(Task task);
	public void processAll();
}
