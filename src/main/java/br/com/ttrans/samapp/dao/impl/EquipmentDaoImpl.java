package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.QueryException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.EquipmentDao;
import br.com.ttrans.samapp.library.DAO;
import br.com.ttrans.samapp.model.Equipment;

@SuppressWarnings("rawtypes")
@Repository
public class EquipmentDaoImpl implements EquipmentDao {

	@Autowired
	private SessionFactory session;

	private static final Logger logger = LoggerFactory.getLogger(DAO.class);

	@Override
	public void add(Equipment equipment, Authentication authentication) {
		equipment.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(equipment);
	}

	@Override
	public void edit(Equipment equipment, Authentication authentication) {
		equipment.setUsr_update(authentication.getName());
		session.getCurrentSession().update(equipment);
	}

	@Override
	public void delete(Equipment equipment, Authentication authentication) {
		equipment.setUsr_update(authentication.getName());
		equipment.setDeleted("*");
		session.getCurrentSession().update(equipment);
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(
				Equipment.class, "equipment");

		crit.createAlias("equipment.type", "equipment_type",
				CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("equipment.model", "equipment_model",
				CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("equipment.manufacturer", "equipment_manufacturer",
				CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("equipment.site", "equipment_site",
				CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("equipment.counter", "equipment_counter",
				CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("equipment.system", "equipment_system",
				CriteriaSpecification.LEFT_JOIN);

		ProjectionList projList = Projections.projectionList();

		projList.add(Projections.property("equipment.equ_id"));
		projList.add(Projections.property("equipment.equ_fixed_asset"));
		projList.add(Projections.property("equipment.equ_service_tag"));
		projList.add(Projections.property("equipment.equ_ip"));
		projList.add(Projections.property("equipment_type.ety_description"));
		projList.add(Projections.property("equipment_model.emo_description"));
		projList.add(Projections
				.property("equipment_manufacturer.ema_description"));
		projList.add(Projections.property("equipment_site.sit_description"));
		projList.add(Projections.property("equipment_counter.cty_description"));
		projList.add(Projections.property("equipment_system.ssy_description"));
		projList.add(Projections.property("equipment.equ_solv_type"));
		projList.add(Projections.property("equipment.equ_warranty"));
		projList.add(Projections.property("equipment.equ_counter_qt"));
		projList.add(Projections.property("equipment.equ_oid"));
		projList.add(Projections.property("equipment.equ_mtbf_prev"));
		projList.add(Projections.property("equipment.equ_mtbf_calc"));
		projList.add(Projections.property("equipment.equ_mtbf_manf"));
		projList.add(Projections.property("equipment.equ_install_date"));
		projList.add(Projections.property("equipment.equ_manufacture_date"));
		projList.add(Projections.property("equipment.equ_acquired_date"));
		projList.add(Projections.property("equipment.equ_remark"));

		crit.setProjection(projList);

		crit.add(Restrictions.ne("equipment.deleted", "*"));
		crit.add(Restrictions.ne("equipment_type.deleted", "*"));
		crit.add(Restrictions.ne("equipment_model.deleted", "*"));
		crit.add(Restrictions.ne("equipment_manufacturer.deleted", "*"));
		crit.add(Restrictions.ne("equipment_site.deleted", "*"));
		crit.add(Restrictions.ne("equipment_counter.deleted", "*"));
		crit.add(Restrictions.ne("equipment_system.deleted", "*"));

		List resultsList = crit.list();

		return resultsList;
	}

	@Override
	public String getOidByIp(String ip) {

		String cReturn = "";

		try {

			Criteria crit = session.getCurrentSession().createCriteria(
					Equipment.class);

			ProjectionList projList = Projections.projectionList();

			projList.add(Projections.property("equ_oid"));

			crit.setProjection(projList);
			crit.add(Restrictions.ne("deleted", "*"));
			crit.add(Restrictions.ne("equ_oid", ""));
			crit.add(Restrictions.eq("equ_ip", ip));

			cReturn = (String) crit.uniqueResult();

		} catch (QueryException e) {
			logger.error(e.getMessage());
		}

		return cReturn;
	}

	@Override
	public Equipment get(String id) {
		return (Equipment) session.getCurrentSession().get(Equipment.class, id);
	}

}
