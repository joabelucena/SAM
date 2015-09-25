package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.QueryException;
import org.hibernate.SessionFactory;
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

@Repository
public class EquipmentDaoImpl implements EquipmentDao {

	@Autowired
	private SessionFactory session;

	private static final Logger logger = LoggerFactory.getLogger(DAO.class);

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

	@Override
	public String getOidByIp(String ip) {

		String cReturn = "";

		try {

			Criteria crit = session.getCurrentSession().createCriteria(
					Equipment.class);

			ProjectionList projList = Projections.projectionList();

			projList.add(Projections.property("oid"));

			crit.setProjection(projList);
			
			crit.add(Restrictions.ne("oid", ""));
			crit.add(Restrictions.eq("ip", ip));

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
