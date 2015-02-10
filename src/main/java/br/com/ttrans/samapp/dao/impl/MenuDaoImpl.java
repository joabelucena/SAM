package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.MenuDao;
import br.com.ttrans.samapp.model.Menu;
import br.com.ttrans.samapp.model.Role;

@Repository
public class MenuDaoImpl implements MenuDao {
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Menu menu) {
		session.getCurrentSession().save(menu);

	}

	@Override
	public void edit(Menu menu) {
		session.getCurrentSession().update(menu);

	}

	@Override
	public void delete(Menu menu) {
		session.getCurrentSession().delete(menu.getId());
	}

	@Override
	public List<Menu> loadMenu(Role role) {
		return session.getCurrentSession().createQuery("from Menu").list();
		
		/*
		Criteria crit = session.getCurrentSession().createCriteria(Menu.class);
		
		return crit.list();
		*/
	}

}
