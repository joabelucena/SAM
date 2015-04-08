package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public int add(ServiceOrder serviceorder, Authentication authentication) {
		return dao.add(serviceorder, authentication);		
	}

	@Transactional
	public void edit(ServiceOrder serviceorder, Authentication authentication) {
		dao.edit(serviceorder, authentication);
	}

	@Transactional
	public void delete(ServiceOrder serviceorder, Authentication authentication) {
		dao.delete(serviceorder, authentication);
	}
	
	@Transactional
	public ServiceOrder get(int id) {
		return dao.get(id);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}
}
