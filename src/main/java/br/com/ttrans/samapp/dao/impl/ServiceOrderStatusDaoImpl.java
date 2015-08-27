package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ServiceOrderStatusDao;
import br.com.ttrans.samapp.model.ServiceOrderStatus;

@Repository
public class ServiceOrderStatusDaoImpl implements ServiceOrderStatusDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(ServiceOrderStatus status, Authentication authentication) {
		status.setInsert(authentication.getName());
		session.getCurrentSession().save(status);

	}

	@Override
	public void edit(ServiceOrderStatus status, Authentication authentication) {
		status.setUpdate(authentication.getName());
		session.getCurrentSession().update(status);

	}

	@Override
	public void delete(ServiceOrderStatus status, Authentication authentication) {
		session.getCurrentSession().delete(status);

	}
	
	@Override
	public ServiceOrderStatus get(int id){
		return (ServiceOrderStatus) session.getCurrentSession().get(ServiceOrderStatus.class, id);
	}

	@Override
	public ServiceOrderStatus findByName(String desc) {
		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrderStatus.class);
		crit.add(Restrictions.eq("desc", desc));
		
		return (ServiceOrderStatus) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceOrderStatus> loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrderStatus.class);
		
		return crit.list();
	}

}
