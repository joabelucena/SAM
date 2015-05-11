package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.ServiceStationDao;
import br.com.ttrans.samapp.model.ServiceStation;
import br.com.ttrans.samapp.service.ServiceStationService;

@Repository
public class ServiceStationServiceImpl implements ServiceStationService {

	@Autowired
	private ServiceStationDao dao;
	
	@Transactional
	public void add(ServiceStation station, Authentication authentication) {
		dao.add(station, authentication);

	}

	@Transactional
	public void edit(ServiceStation station, Authentication authentication) {
		dao.edit(station, authentication);

	}

	@Transactional
	public void delete(ServiceStation station, Authentication authentication) {
		dao.delete(station, authentication);

	}

	@Transactional
	public List<ServiceStation> loadData() {
		return dao.loadData();
	}

}
