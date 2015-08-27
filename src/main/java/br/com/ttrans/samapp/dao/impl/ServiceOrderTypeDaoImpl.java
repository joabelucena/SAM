package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ServiceOrderTypeDao;
import br.com.ttrans.samapp.model.ServiceOrderType;

@Repository
public class ServiceOrderTypeDaoImpl implements ServiceOrderTypeDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(ServiceOrderType type, Authentication authentication) {
		type.setInsert(authentication.getName());
		session.getCurrentSession().save(type);

	}

	@Override
	public void edit(ServiceOrderType type, Authentication authentication) {
		type.setUpdate(authentication.getName());
		session.getCurrentSession().update(type);
	}

	@Override
	public void delete(ServiceOrderType type, Authentication authentication) {
		session.getCurrentSession().delete(type);

	}
	
	@Override
	public ServiceOrderType findByName(String desc) {
		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrderType.class);
		crit.add(Restrictions.eq("desc", desc));
		
		return (ServiceOrderType) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceOrderType> loadData() {

		return session.getCurrentSession().createCriteria(ServiceOrderType.class).list();
		
	}

}
