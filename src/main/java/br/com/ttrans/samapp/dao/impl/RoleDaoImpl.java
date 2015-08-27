package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.RoleDao;
import br.com.ttrans.samapp.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Role role) {
		session.getCurrentSession().save(role);
	}

	@Override
	public void edit(Role role) {
		session.getCurrentSession().update(role);
	}

	@Override
	public void delete(Role role) {
		session.getCurrentSession().delete(role.getId());
	}

	@Override
	public Role get(int id) {
		return (Role) session.getCurrentSession().get(Role.class, id);
	}

	@Override
	public Role findByDesc(String text) {
		Criteria crit = session.getCurrentSession().createCriteria(Role.class);
		crit.add(Restrictions.eq("roleName", text));
		
		return (Role)crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() {
		return session.getCurrentSession().createCriteria(Role.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

}
