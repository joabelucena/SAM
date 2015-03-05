package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.EquipmentModelDao;
import br.com.ttrans.samapp.model.EquipmentModel;

@SuppressWarnings("rawtypes")
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
		model.setUsr_update(authentication.getName());
		model.setDeleted("*");
		session.getCurrentSession().update(model);
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(EquipmentModel.class,"model");
		
		crit.createAlias("model.protocol","model_protocol",CriteriaSpecification.LEFT_JOIN);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("model.emo_id"));
		projList.add(Projections.property("model.emo_description"));
		projList.add(Projections.property("model_protocol.epr_description"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("model.deleted","*"));
		crit.add(Restrictions.ne("model_protocol.deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

}
