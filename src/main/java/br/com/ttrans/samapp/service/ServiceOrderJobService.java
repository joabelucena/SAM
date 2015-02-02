package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.ServiceOrderJob;

public interface ServiceOrderJobService {
	public void add(ServiceOrderJob service);
	public void edit(ServiceOrderJob service);
	public void delete(ServiceOrderJob service);
	public List loadData();
}
