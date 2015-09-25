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
}
