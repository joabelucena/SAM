package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.EquipmentManufacturerDao;
import br.com.ttrans.samapp.model.EquipmentManufacturer;

@Repository
public class EquipmentManufacturerDaoImpl implements EquipmentManufacturerDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(EquipmentManufacturer manufacturer, Authentication authentication) {
		manufacturer.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(manufacturer);
	}

	@Override
	public void edit(EquipmentManufacturer manufacturer, Authentication authentication) {
		manufacturer.setUsr_update(authentication.getName());
		session.getCurrentSession().update(manufacturer);
	}

	@Override
	public void delete(EquipmentManufacturer manufacturer, Authentication authentication) {
		manufacturer.setUsr_update(authentication.getName());
		manufacturer.setDeleted("*");
		session.getCurrentSession().update(manufacturer);
	}

	@Override
	public List loadData() {
		
		Criteria crit = session.getCurrentSession().createCriteria(EquipmentManufacturer.class);
		
		crit.add(Restrictions.ne("deleted","*"));
		
		return crit.list();
	}

}
