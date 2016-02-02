package br.com.ttrans.samapp.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ServiceOrderDao;
import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.model.ServiceOrder;
import br.com.ttrans.samapp.model.ServiceOrderType;

@Repository
public class ServiceOrderDaoImpl implements ServiceOrderDao {
	
	@Autowired
	private DAO dao;
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public int add(ServiceOrder serviceorder, Authentication authentication) {
		serviceorder.setInsert(authentication.getName());
		session.getCurrentSession().save(serviceorder);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceOrder> loadData() {

		return session.getCurrentSession().createCriteria(ServiceOrder.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();

	}
	
	@SuppressWarnings("unchecked")
	public List<ServiceOrder> loadKPIData(Equipment equipment, Date from, Date to){
		
		//Retrieves the maintenance SO type id
		Integer typeId = Integer.parseInt(dao.getMv("SAM_SOCORRET", null));
		
		if(typeId != null){
			
			ServiceOrderType type = new ServiceOrderType(typeId);
			
			return session.getCurrentSession().createCriteria(ServiceOrder.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.between("datetime", from, to))
					.add(Restrictions.eq("type"			, type))
					.add(Restrictions.eq("equipment"	, equipment))
					.addOrder(Order.asc("equipment"))
					.list();	
		}else{
			return new ArrayList<ServiceOrder>();
		}
		
		
	}
}
