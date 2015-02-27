package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.ServiceOrderTypeDao;
import br.com.ttrans.samapp.model.ServiceOrderType;
import br.com.ttrans.samapp.service.ServiceOrderTypeService;

@Repository
public class ServiceOrderTypeServiceImpl implements ServiceOrderTypeService {

	@Autowired
	private ServiceOrderTypeDao dao;

	@Transactional
	public void add(ServiceOrderType type, Authentication authentication) {
		dao.add(type, authentication);
	}

	@Transactional
	public void edit(ServiceOrderType type, Authentication authentication) {
		dao.edit(type, authentication);
	}

	@Transactional
	public void delete(ServiceOrderType type, Authentication authentication) {
		dao.delete(type, authentication);
	}

	@Transactional
	public ServiceOrderType findByName(String desc) {
		return dao.findByName(desc);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
