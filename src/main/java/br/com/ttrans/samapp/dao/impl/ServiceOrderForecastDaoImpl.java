package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ServiceOrderForecastDao;
import br.com.ttrans.samapp.model.ServiceOrderForecast;

@Repository
public class ServiceOrderForecastDaoImpl implements ServiceOrderForecastDao {
	
	@Autowired
	private SessionFactory session;

	@Override
	public void add(ServiceOrderForecast forecast, Authentication authentication) {
		forecast.setInsert(authentication.getName());
		session.getCurrentSession().save(forecast);		
	}

	@Override
	public void edit(ServiceOrderForecast forecast,
			Authentication authentication) {
		forecast.setUpdate(authentication.getName());
		session.getCurrentSession().update(forecast);
		
	}

	@Override
	public void delete(ServiceOrderForecast forecast,
			Authentication authentication) {
		session.getCurrentSession().delete(forecast);
		
	}

	@Override
	public ServiceOrderForecast get(int id) {
		return (ServiceOrderForecast) session.getCurrentSession().get(ServiceOrderForecast.class, id);
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrderForecast.class,"forecast");
		
		return crit.list();
	}


}
