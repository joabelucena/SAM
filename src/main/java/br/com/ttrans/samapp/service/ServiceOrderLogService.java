package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.ServiceOrderLog;

public interface ServiceOrderLogService {
	public void add(ServiceOrderLog log);
	public void edit(ServiceOrderLog log);
	public void delete(ServiceOrderLog log);
	public List loadData();
}
