package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.ServiceOrderType;

public interface ServiceOrderTypeDao {
	public void add(ServiceOrderType type);
	public void edit(ServiceOrderType type);
	public void delete(ServiceOrderType type);
	public ServiceOrderType findByName(String desc);
	public List loadData();
}
