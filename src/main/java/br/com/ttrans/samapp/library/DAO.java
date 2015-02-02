package br.com.ttrans.samapp.library;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.QueryException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.model.Parameters;
import br.com.ttrans.samapp.model.Equipment;

@Repository
public class DAO {

	@Autowired
	private SessionFactory session;

	private static final Logger logger = LoggerFactory.getLogger(DAO.class);
	
	@Transactional
	public boolean ExistCPO(@SuppressWarnings("rawtypes") Class alias,
			Map<String, Object> map) {

		boolean lReturn = false;

		try {
			Criteria crit = session.getCurrentSession().createCriteria(alias);

			crit.add(Restrictions.ne("deleted", "*"));

			for (Map.Entry<String, Object> entry : map.entrySet()) {
				
				if (entry.getValue() != null){
				crit.add(Restrictions.eq(entry.getKey(),
						entry.getValue() instanceof String ? entry.getValue()
								.toString() : entry.getValue()));
				}
			}
			
			lReturn = (crit.list().size() > 0);
			
		} catch (QueryException e) {
			logger.error(e.getMessage());
		}

		return lReturn;
	}

	@Transactional
	public String GetMv(String xParameter, boolean lSearch, String xDefault) {

		String cReturn = "";

		try {
			Criteria crit = session.getCurrentSession().createCriteria(
					Parameters.class);

			ProjectionList projList = Projections.projectionList();

			projList.add(Projections.property("par_value"));

			crit.setProjection(projList);

			crit.add(Restrictions.ne("deleted", "*"));
			crit.add(Restrictions.eq("par_name", xParameter));

			cReturn = crit.uniqueResult().toString();

		} catch (QueryException e) {
			logger.error(e.getMessage());
		}

		return cReturn;
	}
	
}
