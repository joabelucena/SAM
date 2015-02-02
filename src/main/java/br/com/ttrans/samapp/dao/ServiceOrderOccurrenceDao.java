package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.ServiceOrderOccurrence;

public interface ServiceOrderOccurrenceDao {
	public void add(ServiceOrderOccurrence occurrence);
	public void edit(ServiceOrderOccurrence occurrence);
	public void delete(ServiceOrderOccurrence occurrence);
	public List loadData();
}
