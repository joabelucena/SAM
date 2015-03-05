package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.EquipmentProtocolDao;
import br.com.ttrans.samapp.model.EquipmentProtocol;

@SuppressWarnings("rawtypes")
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
		protocol.setUsr_update(authentication.getName());
		protocol.setDeleted("*");
		session.getCurrentSession().update(protocol);

	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(EquipmentProtocol.class);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("epr_id"));
		projList.add(Projections.property("epr_description"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
