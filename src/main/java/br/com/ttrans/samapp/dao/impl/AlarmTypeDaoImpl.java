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

import br.com.ttrans.samapp.dao.AlarmTypeDao;
import br.com.ttrans.samapp.model.AlarmType;

@Repository
public class AlarmTypeDaoImpl implements AlarmTypeDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(AlarmType type, Authentication authentication) {
		type.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(type);
		
	}

	@Override
	public void edit(AlarmType type, Authentication authentication) {
		type.setUsr_update(authentication.getName());
		session.getCurrentSession().update(type);

	}

	@Override
	public void delete(AlarmType type, Authentication authentication) {
		type.setUsr_update(authentication.getName());
		type.setDeleted("*");
		session.getCurrentSession().update(type);

	}

	@Override
	public List loadData() {
		Criteria crit = session.getCurrentSession().createCriteria(AlarmType.class);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("aty_id"));
		projList.add(Projections.property("aty_description"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
