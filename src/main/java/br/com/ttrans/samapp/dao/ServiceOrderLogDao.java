package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.ServiceOrderLog;

public interface ServiceOrderLogDao {
	public void add(ServiceOrderLog log);
	public void edit(ServiceOrderLog log);
	public void delete(ServiceOrderLog log);
	public List loadData();
}
