package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.EventDao;
import br.com.ttrans.samapp.model.Event;

@Repository
public class EventDaoImpl implements EventDao {
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Event event) {
		session.getCurrentSession().save(event);

	}

	@Override
	public void edit(Event event) {
		session.getCurrentSession().update(event);

	}

	@Override
	public void delete(int eveId) {
		session.getCurrentSession().delete(getEvent(eveId));
	}

	@Override
	public Event getEvent(int eveId) {
		return (Event)session.getCurrentSession().get(Event.class, eveId);
	}

	@Override
	public List getAllEvent() {
		return session.getCurrentSession().createQuery("from Events where deleted <> '*'").list();
	}
	
	@Override
	public List loadData() {
		Criteria crit = session.getCurrentSession().createCriteria(Event.class);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("eve_id"));
		projList.add(Projections.property("eve_equipment_id"));
		projList.add(Projections.property("eve_alarm_id"));
		projList.add(Projections.property("eve_datetime"));
		projList.add(Projections.property("eve_solv_user"));
		projList.add(Projections.property("eve_site"));
		projList.add(Projections.property("eve_model"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("deleted", "*"));
		List resultsList = crit.list();
		
		return resultsList;
		
	}

}
