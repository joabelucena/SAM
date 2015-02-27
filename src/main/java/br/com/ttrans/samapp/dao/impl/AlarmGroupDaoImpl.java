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

import br.com.ttrans.samapp.dao.AlarmGroupDao;
import br.com.ttrans.samapp.model.AlarmGroup;

@Repository
public class AlarmGroupDaoImpl implements AlarmGroupDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(AlarmGroup group, Authentication authentication) {
		group.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(group);

	}

	@Override
	public void edit(AlarmGroup group, Authentication authentication) {
		group.setUsr_update(authentication.getName());
		session.getCurrentSession().update(group);

	}

	@Override
	public void delete(AlarmGroup group, Authentication authentication) {
		group.setUsr_update(authentication.getName());
		group.setDeleted("*");
		session.getCurrentSession().update(group);

	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(AlarmGroup.class);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("agr_id"));
		projList.add(Projections.property("agr_description"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
