package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.TechnicianDao;
import br.com.ttrans.samapp.model.Technician;

@Repository
public class TechnicianDaoImpl implements TechnicianDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Technician technician, Authentication authentication) {
		technician.setInsert(authentication.getName());
		session.getCurrentSession().save(technician);

	}

	@Override
	public void edit(Technician technician, Authentication authentication) {
		technician.setUpdate(authentication.getName());
		session.getCurrentSession().update(technician);

	}

	@Override
	public void delete(Technician technician, Authentication authentication) {
		session.getCurrentSession().delete(technician);
	}
	
	@Override
	public Technician get(String id){
		return (Technician) session.getCurrentSession().get(Technician.class, id);
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(Technician.class,"technician");
		
		return crit.list();
	}

}
