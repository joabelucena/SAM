package br.com.ttrans.samapp.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.AlarmDao;
import br.com.ttrans.samapp.dao.EquipmentDao;
import br.com.ttrans.samapp.dao.EventDao;
import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.AlarmType;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;
import br.com.ttrans.samapp.service.EventService;

@Repository
public class EventServiceImpl implements EventService {
	
	private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
	
	private static final String USR_NORM_INSERT = "SAM_NORM";
	
	@Autowired
	private EventDao eventDao;
		
	@Autowired
	private AlarmDao alarmDao;
	
	@Autowired
	private EquipmentDao equipmentDao;
		
	@Transactional
	public void add(Event event) {
		
		Alarm alarm;
		List<String> alarmsToNorm;
		
		logger.info("### Alarm / Equipment: "+event.getAlarm().getId() + " / " + event.getEquipment().getId());
		
		/**
		 * Implement Normalization and filter validation rules.
		 * 
		 */
		//Checa se o alarme esta filtrada, se nao estiver sera adicionado
		if(!eventDao.isFiltered(event)){
			
			/** Recupera alarme e equipamento. Isso eh necessario pois o objeto alarme pode vir
			 * somente com a propriedade 'id' preenchida
			 */
			alarm = alarmDao.get(event.getAlarm().getId());
			
			//Alarme Cadastrado
			if(alarm instanceof Alarm){
				
				//Checa se o tipo do evento eh normalizacao
				if(alarm.getIsNorm().equals("Y")){
					
					event.setSolvUser(USR_NORM_INSERT);
					event.setSolvDate(new Date());
					
					//Retorna Lista com os alarmes que sao normalizados por essa 'normalizacao'
					alarmsToNorm = alarmDao.getNorm(alarm);
					
					//Tratamento para verificar se existem alarmes cadastrados
					if(alarmsToNorm.size() > 0){
						eventDao.normalize(alarmsToNorm, event.getEquipment().getId(), USR_NORM_INSERT);
					}
					
				}
				
			}
			
			try {
				//Adiciona Evento
				eventDao.add(event);
				
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("Erro na insercao");
			}
		}
		
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
	public int countByAlarm(Equipment equipment, Alarm alarm, Date date){
		return eventDao.countByAlarm(equipment, alarm, date);
	}
	
	@Transactional
	public int countByType(Equipment equipment, AlarmType type, Date date){
		return eventDao.countByType(equipment, type, date);
	}
	
	@Transactional
	public List<Long> activeAlarms(Equipment equipment, Alarm alarm){
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
	public List<String[]> loadData(){
		return eventDao.loadData();
	}
}
