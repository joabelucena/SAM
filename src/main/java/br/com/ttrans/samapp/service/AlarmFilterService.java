package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.AlarmFilter;

public interface AlarmFilterService {
	public void add(AlarmFilter filter);
	public void edit(AlarmFilter filter);
	public void delete(AlarmFilter filter);
	public List loadData();
}
