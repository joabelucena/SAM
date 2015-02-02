package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.Event;

public interface EventDao {
	public void add(Event event);
	public void edit(Event event);
	public void delete(int eveId);
	public Event getEvent(int eveId); 
	public List getAllEvent();
	public List loadData();
}
