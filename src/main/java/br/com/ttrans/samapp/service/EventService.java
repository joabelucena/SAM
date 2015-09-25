package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.Event;

public interface EventService {
	public void add(Event event);
	public void edit(Event event, Authentication authentication);
	public void delete(Event event, Authentication authentication);
	public int recognize(Long[] ids, Authentication authentication);
	public int normalize(Long id, Authentication authentication);
	public void normalize(List<String> alarmsId, String equipment, String user);
	public List<Long> activeAlarms(Equipment equipment, Alarm alarm);
	public boolean isFiltered(Event event);
	public Event get(long id); 
	public List<String[]> loadData();
}
