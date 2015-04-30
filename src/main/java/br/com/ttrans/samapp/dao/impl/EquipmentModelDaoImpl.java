package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.EquipmentModelDao;
import br.com.ttrans.samapp.model.EquipmentModel;

@Repository
public class EquipmentModelDaoImpl implements EquipmentModelDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(EquipmentModel model, Authentication authentication) {
		model.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(model);
		
	}

	@Override
	public void edit(EquipmentModel model, Authentication authentication) {
		model.setUsr_update(authentication.getName());
		session.getCurrentSession().update(model);
		
	}

	@Override
	public void delete(EquipmentModel model, Authentication authentication) {
		Query query = session.getCurrentSession().createQuery("update EquipmentModel set deleted = '*', usr_update = :user"
				+ " where id = :id"); 
		
		query.setParameter("id"		, model.getId());
		query.setParameter("user"	, authentication.getName());
		
		query.executeUpdate();
		
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(EquipmentModel.class,"model");
		
		crit.add(Restrictions.ne("model.deleted","*"));
		
		return crit.list();
	}

}
