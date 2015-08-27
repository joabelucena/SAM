package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.ServiceOrderOccurrence;


public interface ServiceOrderOccurrenceDao {
	public void add(ServiceOrderOccurrence occurrence, Authentication authentication);
	public void edit(ServiceOrderOccurrence occurrence, Authentication authentication);
	public void delete(ServiceOrderOccurrence occurrence, Authentication authentication);
	public List<ServiceOrderOccurrence> loadData();
}
