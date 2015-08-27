package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.ServiceOrderLogDao;
import br.com.ttrans.samapp.model.ServiceOrderLog;
import br.com.ttrans.samapp.service.ServiceOrderLogService;

@Repository
public class ServiceOrderLogServiceImpl implements ServiceOrderLogService {

	@Autowired
	private ServiceOrderLogDao dao;
	
	@Transactional
	public void add(ServiceOrderLog log, Authentication authentication) {
		dao.add(log, authentication);
	}

	@Transactional
	public void edit(ServiceOrderLog log, Authentication authentication) {
		dao.edit(log, authentication);
	}

	@Transactional
	public void delete(ServiceOrderLog log, Authentication authentication) {
		dao.delete(log, authentication);
	}

	@Transactional
	public List<ServiceOrderLog> loadData() {
		return dao.loadData();
	}

}
