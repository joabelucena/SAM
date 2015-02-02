package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.ServiceOrderJob;

public interface ServiceOrderJobDao {
	public void add(ServiceOrderJob service);
	public void edit(ServiceOrderJob service);
	public void delete(ServiceOrderJob service);
	public List loadData();
}
