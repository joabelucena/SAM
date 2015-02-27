package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ParameterDao;
import br.com.ttrans.samapp.model.Parameters;

@Repository
public class ParameterDaoImpl implements ParameterDao {
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Parameters parameter, Authentication authentication) {
		parameter.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(parameter);
	}

	@Override
	public void edit(Parameters parameter, Authentication authentication) {
		parameter.setUsr_update(authentication.getName());
		session.getCurrentSession().update(parameter);
	}

	@Override
	public void delete(Parameters parameter, Authentication authentication) {
		parameter.setUsr_update(authentication.getName());
		parameter.setDeleted("*");
		session.getCurrentSession().update(parameter);
	}

	@Override
	public Parameters get(int id) {
		return (Parameters)session.getCurrentSession().get(Parameters.class, id);
	}

	@Override
	public List getAll() {
		return session.getCurrentSession().createQuery("from Parameters where deleted <> '*'").list();
	}

}
