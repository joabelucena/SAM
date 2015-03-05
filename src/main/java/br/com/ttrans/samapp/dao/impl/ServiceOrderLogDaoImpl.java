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

import br.com.ttrans.samapp.dao.ServiceOrderLogDao;
import br.com.ttrans.samapp.model.ServiceOrderLog;

@SuppressWarnings("rawtypes")
@Repository
public class ServiceOrderLogDaoImpl implements ServiceOrderLogDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(ServiceOrderLog log, Authentication authentication) {
		log.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(log);

	}

	@Override
	public void edit(ServiceOrderLog log, Authentication authentication) {
		log.setUsr_update(authentication.getName());
		session.getCurrentSession().update(log);

	}

	@Override
	public void delete(ServiceOrderLog log, Authentication authentication) {
		log.setUsr_update(authentication.getName());
		log.setDeleted("*");
		session.getCurrentSession().update(log);

	}

	@Override
	public List loadData() {
		
		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrderLog.class,"log");
		
		crit.createAlias("log.serviceorder"	,"log_serviceorder",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("log.prevstatus"	,"log_prevstatus",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("log.curstatus"	,"log_curstatus",CriteriaSpecification.LEFT_JOIN);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("log.sol_id"));
		projList.add(Projections.property("log.sol_user_id"));
		projList.add(Projections.property("log.sol_datetime"));
		projList.add(Projections.property("log_serviceorder.sor_id"));
		projList.add(Projections.property("log_prevstatus.sos_description"));
		projList.add(Projections.property("log_curstatus.sos_description"));
		projList.add(Projections.property("log.sol_remarks"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("log.deleted","*"));
		
		crit.add(Restrictions.ne("log_serviceorder.deleted","*"));
		crit.add(Restrictions.ne("log_prevstatus.deleted","*"));
		crit.add(Restrictions.ne("log_curstatus.deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
