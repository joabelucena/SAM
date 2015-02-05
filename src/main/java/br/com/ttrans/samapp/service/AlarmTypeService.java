package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.AlarmType;

public interface AlarmTypeService {
	public void add(AlarmType type);
	public void edit(AlarmType type);
	public void delete(AlarmType type);
	public List loadData();
}
