package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ServiceOrderJobDao;
import br.com.ttrans.samapp.model.ServiceOrderJob;

@Repository
public class ServiceOrderJobDaoImpl implements ServiceOrderJobDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(ServiceOrderJob service, Authentication authentication) {
		service.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(service);

	}

	@Override
	public void edit(ServiceOrderJob service, Authentication authentication) {
		service.setUsr_update(authentication.getName());
		session.getCurrentSession().update(service);

	}

	@Override
	public void delete(ServiceOrderJob service, Authentication authentication) {
		service.setUsr_update(authentication.getName());
		service.setDeleted("*");
		session.getCurrentSession().update(service);

	}
	
	@Override
	public ServiceOrderJob get(String id){
		return (ServiceOrderJob) session.getCurrentSession().get(ServiceOrderJob.class, id);
	}

	@Override
	public List loadData() {
		
		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrderJob.class);
		
		crit.add(Restrictions.ne("deleted","*"));
		
		return crit.list();
	}

}
