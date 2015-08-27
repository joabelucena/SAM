package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
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
		protocol.setInsert(authentication.getName());
		session.getCurrentSession().save(protocol);

	}

	@Override
	public void edit(EquipmentProtocol protocol, Authentication authentication) {
		protocol.setUpdate(authentication.getName());
		session.getCurrentSession().update(protocol);
	}

	@Override
	public void delete(EquipmentProtocol protocol, Authentication authentication) {
		session.getCurrentSession().delete(protocol);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentProtocol> loadData() {

		return session.getCurrentSession().createCriteria(EquipmentProtocol.class).list();
		
	}

}
