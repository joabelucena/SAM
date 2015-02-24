package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ServiceOrderTypeDao;
import br.com.ttrans.samapp.model.ServiceOrderType;
import br.com.ttrans.samapp.model.SiteType;

@Repository
public class ServiceOrderTypeDaoImpl implements ServiceOrderTypeDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(ServiceOrderType type) {
		session.getCurrentSession().save(type);

	}

	@Override
	public void edit(ServiceOrderType type) {
		session.getCurrentSession().update(type);
	}
	
	@Override
	public ServiceOrderType findByName(String desc) {
		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrderType.class);
		crit.add(Restrictions.eq("sot_description", desc));
		crit.add(Restrictions.ne("deleted", "*"));
		
		return (ServiceOrderType) crit.uniqueResult();
	}

	@Override
	public void delete(ServiceOrderType type) {
		type.setDeleted("*");
		session.getCurrentSession().update(type);

	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrderType.class);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("sot_id"));
		projList.add(Projections.property("sot_description"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
