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

import br.com.ttrans.samapp.dao.CounterDao;
import br.com.ttrans.samapp.model.Counter;

@SuppressWarnings("rawtypes")
@Repository
public class CounterDaoImpl implements CounterDao {

	@Autowired
	private SessionFactory session;
		
	@Override
	public void add(Counter counter, Authentication authentication) {
		counter.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(counter);
	}

	@Override
	public void edit(Counter counter, Authentication authentication) {
		counter.setUsr_update(authentication.getName());
		session.getCurrentSession().update(counter);
	}

	@Override
	public void delete(Counter counter, Authentication authentication) {
		counter.setUsr_update(authentication.getName());
		counter.setDeleted("*");
		session.getCurrentSession().update(counter);
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(Counter.class);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("cty_id"));
		projList.add(Projections.property("cty_description"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
