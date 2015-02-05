package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void add(ServiceOrderOccurrence occurrence) {
		dao.add(occurrence);

	}

	@Transactional
	public void edit(ServiceOrderOccurrence occurrence) {
		dao.edit(occurrence);

	}

	@Transactional
	public void delete(ServiceOrderOccurrence occurrence) {
		dao.delete(occurrence);

	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
