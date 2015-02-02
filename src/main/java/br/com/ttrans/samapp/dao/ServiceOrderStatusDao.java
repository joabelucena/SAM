package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.ServiceOrderStatus;

public interface ServiceOrderStatusDao {
	public void add(ServiceOrderStatus status);
	public void edit(ServiceOrderStatus status);
	public void delete(ServiceOrderStatus status);
	public List loadData();
}
