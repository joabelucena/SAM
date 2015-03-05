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

import br.com.ttrans.samapp.dao.ServiceOrderOccurrenceDao;
import br.com.ttrans.samapp.model.ServiceOrderOccurrence;

@SuppressWarnings("rawtypes")
@Repository
public class ServiceOrderOccurrenceDaoImpl implements ServiceOrderOccurrenceDao {

	@Autowired
	private SessionFactory session;

	@Override
	public void add(ServiceOrderOccurrence occurrence, Authentication authentication) {
		occurrence.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(occurrence);

	}

	@Override
	public void edit(ServiceOrderOccurrence occurrence, Authentication authentication) {
		occurrence.setUsr_update(authentication.getName());
		session.getCurrentSession().update(occurrence);

	}

	@Override
	public void delete(ServiceOrderOccurrence occurrence, Authentication authentication) {
		occurrence.setUsr_update(authentication.getName());
		occurrence.setDeleted("*");
		session.getCurrentSession().update(occurrence);

	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrderOccurrence.class,"ocurrencce");
		
		crit.createAlias("ocurrencce.serviceorder"	,"ocurrencce_serviceorder",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("ocurrencce.service"		,"ocurrencce_service",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("ocurrencce.technician"	,"ocurrencce_technician",CriteriaSpecification.LEFT_JOIN);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("ocurrencce.soo_id"));
		projList.add(Projections.property("ocurrencce.soo_start")); 
		projList.add(Projections.property("ocurrencce.soo_end"));
		projList.add(Projections.property("ocurrencce_serviceorder.sor_id"));
		projList.add(Projections.property("ocurrencce_service.sov_description"));
		projList.add(Projections.property("ocurrencce_technician.tec_name"));
		projList.add(Projections.property("ocurrencce.soo_event_type"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("ocurrencce.deleted","*"));
		crit.add(Restrictions.ne("ocurrencce_serviceorder.deleted","*"));
		crit.add(Restrictions.ne("ocurrencce_service.deleted","*"));
		crit.add(Restrictions.ne("ocurrencce_technician.deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
