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

import br.com.ttrans.samapp.dao.AlarmFilterDao;
import br.com.ttrans.samapp.model.AlarmFilter;

@Repository
public class AlarmFilterDaoImpl implements AlarmFilterDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(AlarmFilter filter, Authentication authentication) {
		filter.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(filter);

	}

	@Override
	public void edit(AlarmFilter filter, Authentication authentication) {
		filter.setUsr_update(authentication.getName());
		session.getCurrentSession().update(filter);

	}

	@Override
	public void delete(AlarmFilter filter, Authentication authentication) {
		filter.setUsr_update(authentication.getName());
		filter.setDeleted("*");
		session.getCurrentSession().update(filter);

	}

	@Override
	public List loadData() {
		
		Criteria crit = session.getCurrentSession().createCriteria(AlarmFilter.class,"filter");
		
		crit.createAlias("filter.alarm","filter_alarm",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("filter.equipment","filter_equipment",CriteriaSpecification.LEFT_JOIN);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("filter.alm_id"));
		projList.add(Projections.property("filter_alarm.alm_description"));
		projList.add(Projections.property("filter_equipment.equ_fixed_asset"));
				
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("filter.deleted","*"));
		
		crit.add(Restrictions.ne("alarm_alarm.deleted","*"));
		crit.add(Restrictions.ne("filter_alarm.deleted","*"));
		crit.add(Restrictions.ne("filter_equipment.deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
