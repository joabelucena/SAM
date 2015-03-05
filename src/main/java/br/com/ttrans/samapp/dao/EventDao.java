package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Event;

@SuppressWarnings("rawtypes")
public interface EventDao {
	public void add(Event event);
	public void edit(Event event, Authentication authentication);
	public void delete(Event event, Authentication authentication);
	public Event get(long id); 
	public List getAll();
	public List loadData();
}
