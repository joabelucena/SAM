package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.AlarmType;

public interface AlarmTypeDao {
	public void add(AlarmType type);
	public void edit(AlarmType type);
	public void delete(AlarmType type);
	public List loadData();
}
