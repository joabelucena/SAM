package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Alarm;


public interface AlarmService {
	public void add(Alarm alarm, Authentication authentication);
	public void edit(Alarm alarm, Authentication authentication);
	public void delete(Alarm alarm, Authentication authentication);
	public List<String> getNorm(Alarm norm);
	public Alarm get(String id);
	public List<Alarm> loadData();
}
