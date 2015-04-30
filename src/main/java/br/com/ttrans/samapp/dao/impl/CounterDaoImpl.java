package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
		counter.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(counter);
	}

	@Override
	public void edit(Counter counter, Authentication authentication) {
		counter.setUsr_update(authentication.getName());
		session.getCurrentSession().update(counter);
	}

	@Override
	public void delete(Counter counter, Authentication authentication) {
		Query query = session.getCurrentSession().createQuery("update Counter set deleted = '*', usr_update = :user"
				+ " where id = :id"); 
		
		query.setParameter("id"		, counter.getId());
		query.setParameter("user"	, authentication.getName());
		
		query.executeUpdate();
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(Counter.class);
		
		crit.add(Restrictions.ne("deleted","*"));
		
		return crit.list();
	}

}
