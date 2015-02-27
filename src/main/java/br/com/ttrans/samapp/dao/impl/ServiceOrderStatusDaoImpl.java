package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
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
		status.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(status);

	}

	@Override
	public void edit(ServiceOrderStatus status, Authentication authentication) {
		status.setUsr_update(authentication.getName());
		session.getCurrentSession().update(status);

	}

	@Override
	public void delete(ServiceOrderStatus status, Authentication authentication) {
		status.setUsr_update(authentication.getName());
		status.setDeleted("*");
		session.getCurrentSession().update(status);

	}
	
	@Override
	public ServiceOrderStatus get(int id){
		return (ServiceOrderStatus) session.getCurrentSession().get(ServiceOrderStatus.class, id);
	}

	@Override
	public ServiceOrderStatus findByName(String desc) {
		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrderStatus.class);
		crit.add(Restrictions.eq("sos_description", desc));
		crit.add(Restrictions.ne("deleted", "*"));
		
		return (ServiceOrderStatus) crit.uniqueResult();
	}

	
	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrderStatus.class);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("sos_id"));
		projList.add(Projections.property("sos_description"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
