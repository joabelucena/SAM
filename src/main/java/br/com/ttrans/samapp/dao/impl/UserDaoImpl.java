package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.UserDao;
import br.com.ttrans.samapp.model.Users;
@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory session;
	
	@Override
	public void addUser(Users user) {
		session.getCurrentSession().save(user);
	}

	@Override
	public void editUser(Users user) {
		session.getCurrentSession().update(user);
	}

	@Override
	public void deleteUser(int userId) {
		session.getCurrentSession().delete(findUser(userId));
	}

	@Override
	public Users findUser(int userId) {
		return (Users) session.getCurrentSession().get(Users.class, userId);
	}

	@Override
	public Users findUserByName(String username) {
		Criteria criteria = session.getCurrentSession().createCriteria(Users.class);
		criteria.add(Restrictions.eq("username", username));
		return (Users) criteria.uniqueResult();
	}

	@Override
	public List<Users> getAllUsers() {
		return session.getCurrentSession().createQuery("from Users").list();
	}

}
