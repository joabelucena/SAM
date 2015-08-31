package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Task;

public interface TaskDao {
	public void add(Task task, Authentication authentication);
	public void edit(Task task, Authentication authentication);
	public void delete(Task task, Authentication authentication);
	public Task get(int id);
	public List<Task> loadData();
}
