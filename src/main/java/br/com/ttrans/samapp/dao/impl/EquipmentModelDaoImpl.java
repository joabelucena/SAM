package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
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
		model.setInsert(authentication.getName());
		session.getCurrentSession().save(model);
		
	}

	@Override
	public void edit(EquipmentModel model, Authentication authentication) {
		model.setUpdate(authentication.getName());
		session.getCurrentSession().update(model);
		
	}

	@Override
	public void delete(EquipmentModel model, Authentication authentication) {
		session.getCurrentSession().delete(model);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EquipmentModel> loadData() {

		return session.getCurrentSession().createCriteria(EquipmentModel.class).list();
		
	}
}
