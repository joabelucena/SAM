package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.ServiceOrderJobDao;
import br.com.ttrans.samapp.model.ServiceOrderJob;
import br.com.ttrans.samapp.service.ServiceOrderJobService;

@Repository
public class ServiceOrderJobServiceImpl implements
		ServiceOrderJobService {

	@Autowired
	private ServiceOrderJobDao dao;
	
	@Transactional
	public void add(ServiceOrderJob service) {
		dao.add(service);

	}

	@Transactional
	public void edit(ServiceOrderJob service) {
		dao.edit(service);

	}

	@Transactional
	public void delete(ServiceOrderJob service) {
		dao.delete(service);

	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
