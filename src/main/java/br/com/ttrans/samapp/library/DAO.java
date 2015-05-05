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

import br.com.ttrans.samapp.model.Parameter;

@SuppressWarnings("rawtypes")
@Repository
public class DAO {

	@Autowired
	private SessionFactory session;

	private static final Logger logger = LoggerFactory.getLogger(DAO.class);

	/**
	 * This function checks if the passed entity contains an object that meets the restrictions.
	 * 
	 * @param alias Entity class
	 * @param map Map with searching Restrictions
	 * @return boolean
	 */
	@Transactional
	public boolean ExistCPO(Class alias, Map<String, Object> map) {

		boolean lReturn = false;

		try {
			Criteria crit = session.getCurrentSession().createCriteria(alias);

			crit.add(Restrictions.ne("deleted", "*"));

			for (Map.Entry<String, Object> entry : map.entrySet()) {

				if (entry.getValue() != null) {
					crit.add(Restrictions.eq(entry.getKey(),
							entry.getValue() instanceof String ? entry
									.getValue().toString() : entry.getValue()));
				}
			}

			lReturn = (crit.list().size() > 0);

		} catch (QueryException e) {
			logger.error(e.getMessage());
		}

		return lReturn;
	}

	/**
	 * This function returns any object from any entity, you just have to pass this entity alias (Class) and a map with
	 * Restrictions. Cast is necessary after return.
	 * 
	 * @param alias Entity class
	 * @param map Map with searching Restrictions
	 * @return Object
	 */
	@Transactional
	public Object get(Class alias,
			Map<String, Object> map) {

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
			
			return crit.uniqueResult();
			
			
		} catch (QueryException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * This function returns the Parameter content if exists or xDefault value if doesn't.
	 * 
	 * @param xParameter Parameter name
	 * @param xDefault Default value if not find.
	 * @return Parameter content
	 */
	@Transactional
	public String GetMv(String xParameter, String xDefault) {

		String cReturn = xDefault;

		try {
			Criteria crit = session.getCurrentSession().createCriteria(
					Parameter.class);

			ProjectionList projList = Projections.projectionList();

			projList.add(Projections.property("par_value"));

			crit.setProjection(projList);

			crit.add(Restrictions.ne("deleted", "*"));
			crit.add(Restrictions.eq("par_name", xParameter));

			if (crit.uniqueResult() != null) {
				cReturn = crit.uniqueResult().toString();
			} else {
				logger.info("Parameter: " + xParameter + " doesn't exist.");
			}

		} catch (QueryException e) {
			logger.error(e.getMessage());
		}

		return cReturn;
	}

}