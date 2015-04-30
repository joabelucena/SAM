package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.EquipmentProtocolDao;
import br.com.ttrans.samapp.model.EquipmentProtocol;

@Repository
public class EquipmentProtocolDaoImpl implements EquipmentProtocolDao {
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(EquipmentProtocol protocol, Authentication authentication) {
		protocol.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(protocol);

	}

	@Override
	public void edit(EquipmentProtocol protocol, Authentication authentication) {
		protocol.setUsr_update(authentication.getName());
		session.getCurrentSession().update(protocol);
	}

	@Override
	public void delete(EquipmentProtocol protocol, Authentication authentication) {
		Query query = session.getCurrentSession().createQuery("update EquipmentProtocol set deleted = '*', usr_update = :user"
				+ " where id = :id"); 
		
		query.setParameter("id"		, protocol.getId());
		query.setParameter("user"	, authentication.getName());
		
		query.executeUpdate();

	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(EquipmentProtocol.class);
		
		crit.add(Restrictions.ne("deleted","*"));
		
		return crit.list();
	}

}
