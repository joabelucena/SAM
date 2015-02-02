package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ParameterDao;
import br.com.ttrans.samapp.model.Parameters;

@Repository
public class ParameterDaoImpl implements ParameterDao {
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Parameters parameter) {
		session.getCurrentSession().save(parameter);
	}

	@Override
	public void edit(Parameters parameter) {
		session.getCurrentSession().update(parameter);
	}

	@Override
	public void delete(int parId) {
		session.getCurrentSession().delete(getParameter(parId));
	}

	@Override
	public Parameters getParameter(int parId) {
		return (Parameters)session.getCurrentSession().get(Parameters.class, parId);
	}

	@Override
	public List getAllParameter() {
		return session.getCurrentSession().createQuery("from Parameters where deleted <> '*'").list();
	}

}
