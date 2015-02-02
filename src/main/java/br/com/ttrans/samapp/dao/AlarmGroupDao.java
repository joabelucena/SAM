package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.AlarmGroup;

public interface AlarmGroupDao {
	public void add(AlarmGroup group);
	public void edit(AlarmGroup group);
	public void delete(AlarmGroup group);
	public List loadData();
}
