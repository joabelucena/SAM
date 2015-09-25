package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.ServiceOrderOccurrenceDao;
import br.com.ttrans.samapp.model.ServiceOrderOccurrence;
import br.com.ttrans.samapp.service.ServiceOrderOccurrenceService;

@Repository
public class ServiceOrderOccurrenceServiceImpl implements
		ServiceOrderOccurrenceService {

	@Autowired
	private ServiceOrderOccurrenceDao dao;
	
	@Transactional
	public void add(ServiceOrderOccurrence occurrence, Authentication authentication) {
		dao.add(occurrence, authentication);
	}

	@Transactional
	public void edit(ServiceOrderOccurrence occurrence, Authentication authentication) {
		dao.edit(occurrence, authentication);
	}

	@Transactional
	public void delete(ServiceOrderOccurrence occurrence, Authentication authentication) {
		dao.delete(occurrence, authentication);
	}

	@Transactional
	public List<ServiceOrderOccurrence> loadData() {
		return dao.loadData();
	}

}
