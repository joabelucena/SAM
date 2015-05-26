package br.com.ttrans.samapp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.AlarmDao;
import br.com.ttrans.samapp.dao.AlarmFilterDao;
import br.com.ttrans.samapp.dao.EventDao;
import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.service.EventService;

@Repository
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private AlarmFilterDao filterDao;
	
	@Autowired
	private AlarmDao alarmDao;
	
	@Autowired
	private DAO dao;
	
	@Transactional
	public void add(Event event) {
		
		String cUserNorm =  "SAM_NORM";
		Alarm alarm;
		
		System.out.println("### Start@"+new Date());
		
		/**
		 * Implement Normalization and filter validation rules.
		 * 
		 */
		
		//Checa se o alarme esta filtrada, se nao estiver sera adicionado
		if(!eventDao.isFiltered(event)){
			
			/** Recupera alarme. Isso eh necessario pois o objeto alarme pode vir
			 * somente com a propriedade 'id' preenchida
			 */
			alarm = alarmDao.get(event.getAlarm().getId());
			
			//Alarme Cadastrado
			if(alarm instanceof Alarm){
				
				//Checa se o tipo do evento eh normalizacao
				if(alarm.getIsNorm() == 1){
					
					event.setSolvUser(cUserNorm);
					event.setSolvDate(new Date());
					
					//Retorna Lista com os alarmes que sao normalizados por essa 'normalizacao'
					eventDao.normalize(alarmDao.getNorm(alarm), event.getEquipment().getId(), cUserNorm);
					
				}
				
			}
			
			
			
			//Adiciona Evento
			eventDao.add(event);
		}
		
		System.out.println("### Ended@"+ new Date());
		
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
	public void normalize(List<String> alarmsId, String equipment, String user){
		eventDao.normalize(alarmsId, equipment,user);
	}
	
	@Transactional
	public List activeAlarms(Equipment equipment, Alarm alarm){
		return eventDao.activeAlarms(equipment, alarm);
	}
	
	@Transactional
	public boolean isFiltered(Event event){
		return eventDao.isFiltered(event);
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
