package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ServiceStationDao;
import br.com.ttrans.samapp.model.ServiceStation;

@Repository
public class ServiceStationDaoImpl implements ServiceStationDao {
	
	@Autowired
	private SessionFactory session;

	@Override
	public void add(ServiceStation station, Authentication authentication) {
		station.setInsert(authentication.getName());
		session.getCurrentSession().save(station);

	}

	@Override
	public void edit(ServiceStation station, Authentication authentication) {
		station.setUpdate(authentication.getName());
		session.getCurrentSession().update(station);

	}

	@Override
	public void delete(ServiceStation station, Authentication authentication) {
		session.getCurrentSession().delete(station);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceStation> loadData() {
		
		return session.getCurrentSession().createCriteria(ServiceStation.class).list();
				
	}

}
