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

import br.com.ttrans.samapp.dao.AlarmDao;
import br.com.ttrans.samapp.model.Alarm;

@Repository
public class AlarmDaoImpl implements AlarmDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Alarm alarm, Authentication authentication) {
		alarm.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(alarm);

	}

	@Override
	public void edit(Alarm alarm, Authentication authentication) {
		alarm.setUsr_update(authentication.getName());
		session.getCurrentSession().update(alarm);

	}

	@Override
	public void delete(Alarm alarm, Authentication authentication) {
		alarm.setUsr_update(authentication.getName());
		alarm.setDeleted("*");
		session.getCurrentSession().update(alarm);

	}
	
	@Override
	public Alarm get(String id){
		return (Alarm) session.getCurrentSession().get(Alarm.class, id);
	}

	@Override
	public List loadData() {
		
		Criteria crit = session.getCurrentSession().createCriteria(Alarm.class,"alarm");
		
		crit.createAlias("alarm.alarm","alarm_alarm",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("alarm.group","alarm_group",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("alarm.type","alarm_type",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("alarm.model","alarm_model",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("alarm.severity","alarm_severity",CriteriaSpecification.LEFT_JOIN);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("alarm.alm_id"));
		projList.add(Projections.property("alarm.alm_description"));
		projList.add(Projections.property("alarm_alarm.alm_description"));
		projList.add(Projections.property("alarm_group.agr_description"));
		projList.add(Projections.property("alarm_type.aty_description"));
		projList.add(Projections.property("alarm_model.emo_description"));
		projList.add(Projections.property("alarm_severity.sle_descripton"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("alarm.deleted","*"));
		
		crit.add(Restrictions.ne("alarm_alarm.deleted","*"));
		crit.add(Restrictions.ne("alarm_group.deleted","*"));
		crit.add(Restrictions.ne("alarm_type.deleted","*"));
		crit.add(Restrictions.ne("alarm_model.deleted","*"));
		crit.add(Restrictions.ne("alarm_severity.deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
