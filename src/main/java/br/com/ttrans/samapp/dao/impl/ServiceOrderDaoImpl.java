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

import br.com.ttrans.samapp.dao.ServiceOrderDao;
import br.com.ttrans.samapp.model.ServiceOrder;

@SuppressWarnings("rawtypes")
@Repository
public class ServiceOrderDaoImpl implements ServiceOrderDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public int add(ServiceOrder serviceorder, Authentication authentication) {
		serviceorder.setUsr_insert(authentication.getName());
		session.getCurrentSession().persist(serviceorder);
		return serviceorder.getSor_id();

	}

	@Override
	public void edit(ServiceOrder serviceorder, Authentication authentication) {
		serviceorder.setUsr_update(authentication.getName());
		session.getCurrentSession().update(serviceorder);

	}

	@Override
	public void delete(ServiceOrder serviceorder, Authentication authentication) {
		serviceorder.setUsr_update(authentication.getName());
		serviceorder.setDeleted("*");
		session.getCurrentSession().update(serviceorder);
	}
	

	@Override
	public ServiceOrder get(int id) {
		return (ServiceOrder) session.getCurrentSession().get(ServiceOrder.class, id);
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(ServiceOrder.class,"serviceorder");
		
		crit.createAlias("serviceorder.type"		,"serviceorder_type",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.status"		,"serviceorder_status",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.event"		,"serviceorder_event",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.parent"		,"serviceorder_parent",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.technician"	,"serviceorder_technician",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.priority"	,"serviceorder_priority",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.equipment"	,"serviceorder_equipment",CriteriaSpecification.LEFT_JOIN);
		
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("serviceorder.sor_id"));
		
		projList.add(Projections.property("serviceorder_equipment.equ_id"));
		
		
		projList.add(Projections.property("serviceorder_type.sot_description"));
		projList.add(Projections.property("serviceorder_status.sos_description"));
		projList.add(Projections.property("serviceorder_event.eve_id"));
		projList.add(Projections.property("serviceorder_parent.sor_id"));
		projList.add(Projections.property("serviceorder_technician.tec_name"));
		projList.add(Projections.property("serviceorder_priority.sle_description"));
		
		projList.add(Projections.property("serviceorder.sor_start_forecast"));
		projList.add(Projections.property("serviceorder.sor_start"));
		projList.add(Projections.property("serviceorder.sor_end_forecast"));
		projList.add(Projections.property("serviceorder.sor_end"));
		
		projList.add(Projections.property("sor_equipment_stop"));
		projList.add(Projections.property("sor_remarks"));

		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("serviceorder.deleted","*"));
		
		//crit.setResultTransformer(Transformers.aliasToBean(ServiceOrder.class));
		
		List resultsList = crit.list();
		
		return resultsList;		

	}
}
