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

import br.com.ttrans.samapp.dao.SeverityLevelDao;
import br.com.ttrans.samapp.model.SeverityLevel;

@Repository
public class SeverityLevelDaoImpl implements SeverityLevelDao {

	@Autowired
	private SessionFactory session;

	@Override
	public void add(SeverityLevel severity, Authentication authentication) {
		severity.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(severity);

	}

	@Override
	public void edit(SeverityLevel severity, Authentication authentication) {
		severity.setUsr_update(authentication.getName());
		session.getCurrentSession().update(severity);

	}

	@Override
	public void delete(SeverityLevel severity, Authentication authentication) {
		severity.setUsr_update(authentication.getName());
		severity.setDeleted("*");
		session.getCurrentSession().update(severity);

	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(SeverityLevel.class);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("sle_id"));
		projList.add(Projections.property("sle_description"));
		projList.add(Projections.property("sle_display_color"));
		projList.add(Projections.property("sle_time_service"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
