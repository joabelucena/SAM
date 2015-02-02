package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.EventDao;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.service.EventService;

@Repository
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDao eventDao;
	
	@Transactional
	public void add(Event event) {
		eventDao.add(event);

	}

	@Transactional
	public void edit(Event event) {
		eventDao.edit(event);

	}

	@Transactional
	public void delete(int eveId) {
		eventDao.delete(eveId);

	}

	@Transactional
	public Event getEvent(int eveId) {
		return eventDao.getEvent(eveId);
	}

	@Transactional
	public List getAllEvent() {
		return eventDao.getAllEvent();
	}
	
	@Transactional
	public List loadData(){
		return eventDao.loadData();
	}

}
