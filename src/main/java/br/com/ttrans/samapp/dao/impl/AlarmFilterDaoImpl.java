package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.AlarmFilterDao;
import br.com.ttrans.samapp.model.AlarmFilter;

@Repository
public class AlarmFilterDaoImpl implements AlarmFilterDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(AlarmFilter filter, Authentication authentication) {
		filter.setInsert(authentication.getName());
		session.getCurrentSession().save(filter);

	}
	
	@Override
	public void edit(AlarmFilter filter, Authentication authentication) {
		filter.setUpdate(authentication.getName());
		session.getCurrentSession().update(filter);

	}
	
	@Override
	public void delete(AlarmFilter filter, Authentication authentication) {
		session.getCurrentSession().delete(filter);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AlarmFilter> loadData() {
		
		return session.getCurrentSession().createCriteria(AlarmFilter.class).list();

	}

}
