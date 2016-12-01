package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.UserDao;
import br.com.ttrans.samapp.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(User user, Authentication authentication) {
		session.getCurrentSession().save(user);
	}

	@Override
	public void edit(User user, Authentication authentication) {
		session.getCurrentSession().update(user);
	}

	@Override
	public void delete(User user, Authentication authentication) {
		session.getCurrentSession().delete(user);
	}

	@Override
	public User findUserByName(String username) {
		Criteria criteria = session.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		return (User) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadData() {
		return session.getCurrentSession().createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

}
