package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.SubSystemDao;
import br.com.ttrans.samapp.model.SubSystem;

@Repository
public class SubSystemDaoImpl implements SubSystemDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(SubSystem system, Authentication authentication) {
		system.setInsert(authentication.getName());
		session.getCurrentSession().save(system);
	}

	@Override
	public void edit(SubSystem system, Authentication authentication) {
		system.setUpdate(authentication.getName());
		session.getCurrentSession().update(system);
	}

	@Override
	public void delete(SubSystem system, Authentication authentication) {
		session.getCurrentSession().delete(system);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubSystem> loadData() {

		return session.getCurrentSession().createCriteria(SubSystem.class).list();
	}

}
