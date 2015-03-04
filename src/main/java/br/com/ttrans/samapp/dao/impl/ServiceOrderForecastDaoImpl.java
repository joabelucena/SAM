package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
		forecast.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(forecast);		
	}

	@Override
	public void edit(ServiceOrderForecast forecast,
			Authentication authentication) {
		forecast.setUsr_update(authentication.getName());
		session.getCurrentSession().update(forecast);
		
	}

	@Override
	public void delete(ServiceOrderForecast forecast,
			Authentication authentication) {
		forecast.setUsr_update(authentication.getName());
		forecast.setDeleted("*");
		session.getCurrentSession().update(forecast);
		
	}

	@Override
	public ServiceOrderForecast get(int id) {
		return (ServiceOrderForecast) session.getCurrentSession().get(ServiceOrderForecast.class, id);
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrderForecast.class,"forecast");
		
		crit.createAlias("forecast.severity","forecast_severity",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("forecast.system"	,"forecast_system",CriteriaSpecification.LEFT_JOIN);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("forecast.sof_id"));
		projList.add(Projections.property("forecast_severity.sle_description"));
		projList.add(Projections.property("forecast_system.ssy_description"));
		projList.add(Projections.property("forecast.sof_start_forecast"));
		projList.add(Projections.property("forecast.sof_end_forecast"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("forecast.deleted","*"));
		crit.add(Restrictions.ne("forecast_severity.deleted","*"));
		crit.add(Restrictions.ne("forecast_system.deleted","*"));
		
		return crit.list();
	}


}
