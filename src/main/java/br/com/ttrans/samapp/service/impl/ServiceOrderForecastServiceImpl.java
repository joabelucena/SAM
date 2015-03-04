package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.ServiceOrderForecastDao;
import br.com.ttrans.samapp.model.ServiceOrderForecast;
import br.com.ttrans.samapp.service.ServiceOrderForecastService;

@Repository
public class ServiceOrderForecastServiceImpl implements
		ServiceOrderForecastService {

	@Autowired
	private ServiceOrderForecastDao dao;
		
	@Transactional
	public void add(ServiceOrderForecast forecast, Authentication authentication) {
		dao.add(forecast, authentication);
	}

	@Transactional
	public void edit(ServiceOrderForecast forecast,
			Authentication authentication) {
		dao.edit(forecast, authentication);
	}

	@Transactional
	public void delete(ServiceOrderForecast forecast,
			Authentication authentication) {
		dao.delete(forecast, authentication);
	}

	@Transactional
	public ServiceOrderForecast get(int id) {
		return dao.get(id);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}
}
