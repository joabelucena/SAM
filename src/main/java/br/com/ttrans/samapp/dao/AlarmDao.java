package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.Alarm;

public interface AlarmDao {
	public void add(Alarm alarm);
	public void edit(Alarm alarm);
	public void delete(Alarm alarm);
	public List loadData();
}
