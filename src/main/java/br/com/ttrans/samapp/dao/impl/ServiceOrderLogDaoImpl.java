package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ServiceOrderLogDao;
import br.com.ttrans.samapp.model.ServiceOrderLog;

@Repository
public class ServiceOrderLogDaoImpl implements ServiceOrderLogDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(ServiceOrderLog log, Authentication authentication) {
		log.setInsert(authentication.getName());
		session.getCurrentSession().save(log);

	}

	@Override
	public void edit(ServiceOrderLog log, Authentication authentication) {
		log.setUpdate(authentication.getName());
		session.getCurrentSession().update(log);

	}

	@Override
	public void delete(ServiceOrderLog log, Authentication authentication) {
		session.getCurrentSession().delete(log);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceOrderLog> loadData() {
		
		return session.getCurrentSession().createCriteria(ServiceOrderLog.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

}
