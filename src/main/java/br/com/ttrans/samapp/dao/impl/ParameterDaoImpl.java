package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.ParameterDao;
import br.com.ttrans.samapp.model.Parameter;


@Repository
public class ParameterDaoImpl implements ParameterDao {
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Parameter parameter, Authentication authentication) {
		parameter.setInsert(authentication.getName());
		session.getCurrentSession().save(parameter);
	}

	@Override
	public void edit(Parameter parameter, Authentication authentication) {
		parameter.setUpdate(authentication.getName());
		session.getCurrentSession().update(parameter);
	}

	@Override
	public void delete(Parameter parameter, Authentication authentication) {
		session.getCurrentSession().delete(parameter);
	}

	@Override
	public Parameter get(int id) {
		return (Parameter)session.getCurrentSession().get(Parameter.class, id);
	}

	@Override
	public List<Parameter> getAll() {
		return session.getCurrentSession().createQuery("from Parameter").list();
	}

}
