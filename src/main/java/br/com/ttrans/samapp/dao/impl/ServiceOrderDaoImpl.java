package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ServiceOrderDao;
import br.com.ttrans.samapp.model.ServiceOrder;

@Repository
public class ServiceOrderDaoImpl implements ServiceOrderDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(ServiceOrder serviceorder) {
		session.getCurrentSession().persist(serviceorder);

	}

	@Override
	public void edit(ServiceOrder serviceorder) {
		session.getCurrentSession().update(serviceorder);

	}

	@Override
	public void delete(ServiceOrder serviceorder) {
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
		crit.createAlias("serviceorder.severity"	,"serviceorder_severity",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("serviceorder.equipment"	,"serviceorder_equipment",CriteriaSpecification.LEFT_JOIN);
		
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("serviceorder.sor_id"));
		
		projList.add(Projections.property("serviceorder_type.sot_description"));
		projList.add(Projections.property("serviceorder_status.sos_description"));
		projList.add(Projections.property("serviceorder_event.eve_id"));
		projList.add(Projections.property("serviceorder_parent.sor_id"));
		projList.add(Projections.property("serviceorder_technician.tec_name"));
		projList.add(Projections.property("serviceorder_severity.sle_description"));
		projList.add(Projections.property("serviceorder_equipment.equ_fixed_asset"));
		
		projList.add(Projections.property("serviceorder.sor_start_forecast"));
		projList.add(Projections.property("serviceorder.sor_start"));
		projList.add(Projections.property("serviceorder.sor_end_forecast"));
		projList.add(Projections.property("serviceorder.sor_end"));
		projList.add(Projections.property("sor_equipment_stop"));
		projList.add(Projections.property("sor_remark"));
		
						
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("serviceorder.deleted","*"));
		
		crit.add(Restrictions.ne("serviceorder_type.deleted","*"));
		crit.add(Restrictions.ne("serviceorder_status.deleted","*"));
		crit.add(Restrictions.ne("serviceorder_event.deleted","*"));
		crit.add(Restrictions.ne("serviceorder_parent.deleted","*"));
		crit.add(Restrictions.ne("serviceorder_technician.deleted","*"));
		crit.add(Restrictions.ne("serviceorder_severity.deleted","*"));
		crit.add(Restrictions.ne("serviceorder_equipment.deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;		

	}
}
