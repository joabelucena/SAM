package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.ServiceOrderStatusDao;
import br.com.ttrans.samapp.model.ServiceOrderStatus;
import br.com.ttrans.samapp.service.ServiceOrderStatusService;

@Repository
public class ServiceOrderStatusServiceImpl implements ServiceOrderStatusService {

	@Autowired
	private ServiceOrderStatusDao dao;
	
	@Transactional
	public void add(ServiceOrderStatus status, Authentication authentication) {
		dao.add(status, authentication);
	}

	@Transactional
	public void edit(ServiceOrderStatus status, Authentication authentication) {
		dao.edit(status, authentication);
	}

	@Transactional
	public void delete(ServiceOrderStatus status, Authentication authentication) {
		dao.delete(status, authentication);
	}
	
	@Transactional
	public ServiceOrderStatus get(int id){
		return dao.get(id);
	}
	
	@Transactional
	public ServiceOrderStatus findByName(String desc) {
		return dao.findByName(desc);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
