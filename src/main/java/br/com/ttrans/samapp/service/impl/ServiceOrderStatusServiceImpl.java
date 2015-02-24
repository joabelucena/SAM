package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void add(ServiceOrderStatus status) {
		dao.add(status);

	}

	@Transactional
	public void edit(ServiceOrderStatus status) {
		dao.edit(status);

	}

	@Transactional
	public void delete(ServiceOrderStatus status) {
		dao.delete(status);
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
