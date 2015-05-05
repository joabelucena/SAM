package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.CounterDao;
import br.com.ttrans.samapp.model.Counter;

@Repository
public class CounterDaoImpl implements CounterDao {

	@Autowired
	private SessionFactory session;
		
	@Override
	public void add(Counter counter, Authentication authentication) {
		counter.setInsert(authentication.getName());
		session.getCurrentSession().save(counter);
	}

	@Override
	public void edit(Counter counter, Authentication authentication) {
		counter.setUpdate(authentication.getName());
		session.getCurrentSession().update(counter);
	}

	@Override
	public void delete(Counter counter, Authentication authentication) {
		session.getCurrentSession().delete(counter);
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(Counter.class);
		
		return crit.list();
	}

}
