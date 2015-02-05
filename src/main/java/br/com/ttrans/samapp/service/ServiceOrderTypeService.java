package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.ServiceOrderType;

public interface ServiceOrderTypeService {
	public void add(ServiceOrderType type);
	public void edit(ServiceOrderType type);
	public void delete(ServiceOrderType type);
	public List loadData();
}
