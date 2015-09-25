package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.OperationalStateDao;
import br.com.ttrans.samapp.model.OperationalState;

@Repository
public class OperationalStateDaoImpl implements OperationalStateDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(OperationalState state, Authentication authentication) {
		state.setInsert(authentication.getName());
		session.getCurrentSession().save(state);
	}

	@Override
	public void edit(OperationalState state, Authentication authentication) {
		state.setUpdate(authentication.getName());
		session.getCurrentSession().update(state);
	}

	@Override
	public void delete(OperationalState state, Authentication authentication) {
		session.getCurrentSession().delete(state);
	}
	
	@Override
	public OperationalState get(String id){
		return (OperationalState) session.getCurrentSession().get(OperationalState.class, id);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperationalState> loadData() {

		return session.getCurrentSession().createCriteria(OperationalState.class).list();
		
	}

}
