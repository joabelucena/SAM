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

import br.com.ttrans.samapp.dao.SubSystemDao;
import br.com.ttrans.samapp.model.SubSystem;

@SuppressWarnings("rawtypes")
@Repository
public class SubSystemDaoImpl implements SubSystemDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(SubSystem system, Authentication authentication) {
		system.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(system);
	}

	@Override
	public void edit(SubSystem system, Authentication authentication) {
		system.setUsr_update(authentication.getName());
		session.getCurrentSession().update(system);
	}

	@Override
	public void delete(SubSystem system, Authentication authentication) {
		system.setUsr_update(authentication.getName());
		system.setDeleted("*");
		session.getCurrentSession().update(system);
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(SubSystem.class);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("ssy_id"));
		projList.add(Projections.property("ssy_description"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
