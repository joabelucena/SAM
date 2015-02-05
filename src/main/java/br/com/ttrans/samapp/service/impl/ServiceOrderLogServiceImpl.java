package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void add(ServiceOrderLog log) {
		dao.add(log);

	}

	@Transactional
	public void edit(ServiceOrderLog log) {
		dao.edit(log);

	}

	@Transactional
	public void delete(ServiceOrderLog log) {
		dao.delete(log);

	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
