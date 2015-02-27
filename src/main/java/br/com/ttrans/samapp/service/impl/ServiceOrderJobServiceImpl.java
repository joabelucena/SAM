package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public void add(ServiceOrderJob service, Authentication authentication) {
		dao.add(service, authentication);
	}

	@Transactional
	public void edit(ServiceOrderJob service, Authentication authentication) {
		dao.edit(service, authentication);
	}

	@Transactional
	public void delete(ServiceOrderJob service, Authentication authentication) {
		dao.delete(service, authentication);
	}
	
	@Transactional
	public ServiceOrderJob get(String id) {
		return dao.get(id);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
