package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.ServiceOrder;

public interface ServiceOrderService {
	public void add(ServiceOrder serviceorder);
	public void edit(ServiceOrder serviceorder);
	public void delete(ServiceOrder serviceorder);
	public ServiceOrder get(int id);
	public List loadData();
}
