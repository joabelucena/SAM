package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.ServiceOrderDao;
import br.com.ttrans.samapp.model.ServiceOrder;
import br.com.ttrans.samapp.service.ServiceOrderService;

@Repository
public class ServiceOrderServiceImpl implements ServiceOrderService {

	@Autowired
	private ServiceOrderDao dao;

	@Transactional
	public void add(ServiceOrder serviceorder) {
		dao.add(serviceorder);
		
	}

	@Transactional
	public void edit(ServiceOrder serviceorder) {
		dao.edit(serviceorder);
		
	}

	@Transactional
	public void delete(ServiceOrder serviceorder) {
		dao.delete(serviceorder);
		
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

	
}
