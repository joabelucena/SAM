package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.EquipmentTypeDao;
import br.com.ttrans.samapp.model.EquipmentType;

@Repository
public class EquipmentTypeDaoImpl implements EquipmentTypeDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(EquipmentType type, Authentication authentication) {
		type.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(type);
	}

	@Override
	public void edit(EquipmentType type, Authentication authentication) {
		type.setUsr_update(authentication.getName());
		session.getCurrentSession().update(type);
	}

	@Override
	public void delete(EquipmentType type, Authentication authentication) {
		type.setUsr_update(authentication.getName());
		type.setDeleted("*");
		session.getCurrentSession().update(type);
	}

	@Override
	public List loadData() {
		
		Criteria crit = session.getCurrentSession().createCriteria(EquipmentType.class);
		
		crit.add(Restrictions.ne("deleted","*"));
		
		return crit.list();
	}

}
