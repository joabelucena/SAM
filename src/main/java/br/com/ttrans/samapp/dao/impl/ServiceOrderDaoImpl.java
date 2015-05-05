package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ServiceOrderDao;
import br.com.ttrans.samapp.model.ServiceOrder;

@Repository
public class ServiceOrderDaoImpl implements ServiceOrderDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public int add(ServiceOrder serviceorder, Authentication authentication) {
		serviceorder.setInsert(authentication.getName());
		session.getCurrentSession().persist(serviceorder);
		return serviceorder.getId();

	}

	@Override
	public void edit(ServiceOrder serviceorder, Authentication authentication) {
		serviceorder.setUpdate(authentication.getName());
		session.getCurrentSession().update(serviceorder);

	}

	@Override
	public void delete(ServiceOrder serviceorder, Authentication authentication) {
		session.getCurrentSession().delete(serviceorder);
	}
	

	@Override
	public ServiceOrder get(int id) {
		return (ServiceOrder) session.getCurrentSession().get(ServiceOrder.class, id);
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrder.class,"serviceorder");
		/*
		crit.createAlias("serviceorder.type"		,"serviceorder_type"		,CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.status"		,"serviceorder_status"		,CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.event"		,"serviceorder_event"		,CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.parent"		,"serviceorder_parent"		,CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.technician"	,"serviceorder_technician"	,CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.priority"	,"serviceorder_priority"	,CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.equipment"	,"serviceorder_equipment"	,CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.log"			,"serviceorder_log"			,CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.occurrences"	,"serviceorder_occurrences"	,CriteriaSpecification.LEFT_JOIN);
		
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("serviceorder.sor_id").as("sor_id"));
		
		projList.add(Projections.property("serviceorder.equipment").as("equipment"));
		
		projList.add(Projections.property("serviceorder.type").as("type"));
		projList.add(Projections.property("serviceorder.status").as("status"));
		projList.add(Projections.property("serviceorder.event").as("event"));
		projList.add(Projections.property("serviceorder.parent").as("parent"));
		projList.add(Projections.property("serviceorder.technician").as("technician"));
		projList.add(Projections.property("serviceorder.priority").as("priority"));
		
		projList.add(Projections.property("serviceorder.sor_start_forecast").as("sor_start_forecast"));
		projList.add(Projections.property("serviceorder.sor_start").as("sor_start"));
		projList.add(Projections.property("serviceorder.sor_end_forecast").as("sor_end_forecast"));
		projList.add(Projections.property("serviceorder.sor_end").as("sor_end"));
		
		projList.add(Projections.property("serviceorder.sor_equipment_stop").as("sor_equipment_stop"));
		projList.add(Projections.property("serviceorder.sor_remarks").as("sor_remarks"));
		*/
		
		/* TODO Verificar pq nao aparece no carregamento do JSON
		projList.add(Projections.property("serviceorder.log").as("log"));
		projList.add(Projections.property("serviceorder.occurrences").as("occurrences"));
		*/	
		
		
		/*
		crit.setProjection(projList);
		
		crit.setResultTransformer(Transformers.aliasToBean(ServiceOrder.class));
		
		List resultsList = crit.list();
		*/
		return crit.list();		

	}
}
