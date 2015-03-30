package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public void edit(Event event, Authentication authentication) {
		eventDao.edit(event, authentication);
	}

	@Transactional
	public void delete(Event event, Authentication authentication) {
		eventDao.delete(event, authentication);
	}
	
	@Transactional
	public int recognize(Long[] ids, Authentication authentication){
		return eventDao.recognize(ids, authentication);
	}
	
	@Transactional
	public int normalize(Long id, Authentication authentication){
		return eventDao.normalize(id, authentication);
	}

	@Transactional
	public Event get(long id) {
		return eventDao.get(id);
	}

	@Transactional
	public List getAll() {
		return eventDao.getAll();
	}
	
	@Transactional
	public List loadData(){
		return eventDao.loadData();
	}
}
