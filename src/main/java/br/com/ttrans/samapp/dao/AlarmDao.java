package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Alarm;

@SuppressWarnings("rawtypes")
public interface AlarmDao {
	public void add(Alarm alarm, Authentication authentication);
	public void edit(Alarm alarm, Authentication authentication);
	public void delete(Alarm alarm, Authentication authentication);
	public Alarm get(String id);
	public List loadData();
}
