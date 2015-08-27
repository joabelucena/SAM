package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ServiceOrderOccurrenceDao;
import br.com.ttrans.samapp.model.ServiceOrderOccurrence;

@Repository
public class ServiceOrderOccurrenceDaoImpl implements ServiceOrderOccurrenceDao {

	@Autowired
	private SessionFactory session;

	@Override
	public void add(ServiceOrderOccurrence occurrence, Authentication authentication) {
		occurrence.setInsert(authentication.getName());
		session.getCurrentSession().save(occurrence);

	}

	@Override
	public void edit(ServiceOrderOccurrence occurrence, Authentication authentication) {
		occurrence.setUpdate(authentication.getName());
		session.getCurrentSession().update(occurrence);

	}

	@Override
	public void delete(ServiceOrderOccurrence occurrence, Authentication authentication) {
		session.getCurrentSession().delete(occurrence);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceOrderOccurrence> loadData() {

		return session.getCurrentSession().createCriteria(ServiceOrderOccurrence.class)
				.list();
		
	}
}
