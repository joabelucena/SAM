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

import br.com.ttrans.samapp.dao.OperationalStateDao;
import br.com.ttrans.samapp.model.OperationalState;

@Repository
public class OperationalStateDaoImpl implements OperationalStateDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(OperationalState state, Authentication authentication) {
		state.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(state);
	}

	@Override
	public void edit(OperationalState state, Authentication authentication) {
		state.setUsr_update(authentication.getName());
		session.getCurrentSession().update(state);
	}

	@Override
	public void delete(OperationalState state, Authentication authentication) {
		state.setUsr_update(authentication.getName());
		state.setDeleted("*");
		session.getCurrentSession().update(state);
	}
	
	@Override
	public OperationalState get(String id){
		return (OperationalState) session.getCurrentSession().get(OperationalState.class, id);
		
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(OperationalState.class,"state");
		
		crit.createAlias("state.model","state_model",CriteriaSpecification.LEFT_JOIN);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("state.ost_id"));
		projList.add(Projections.property("state.ost_description"));
		projList.add(Projections.property("state_model.emo_description"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("state.deleted","*"));
		crit.add(Restrictions.ne("state_model.deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
