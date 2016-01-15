package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.EquipmentDao;
import br.com.ttrans.samapp.library.IP;
import br.com.ttrans.samapp.model.Equipment;

@Repository
public class EquipmentDaoImpl implements EquipmentDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Equipment equipment, Authentication authentication) {
		equipment.setInsert(authentication.getName());
		session.getCurrentSession().persist(equipment);
	}

	@Override
	public void edit(Equipment equipment, Authentication authentication) {
		equipment.setUpdate(authentication.getName());
		session.getCurrentSession().update(equipment);
	}

	@Override
	public void delete(Equipment equipment, Authentication authentication) {
		session.getCurrentSession().delete(equipment);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipment> loadData() {
		return session.getCurrentSession().createCriteria(Equipment.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipment> loadData(int start, int limit) {
		return session.getCurrentSession().createCriteria(Equipment.class)
				.setFirstResult(start)
				.setMaxResults(limit)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

	@Override
	public Equipment get(IP ip) {
		
		return (Equipment) session.getCurrentSession().createCriteria(Equipment.class)
				.add(Restrictions.eq("ip", ip.getIp()))
				.uniqueResult();
	}

	@Override
	public Equipment get(String id) {
		return (Equipment) session.getCurrentSession().get(Equipment.class, id);
	}

}
