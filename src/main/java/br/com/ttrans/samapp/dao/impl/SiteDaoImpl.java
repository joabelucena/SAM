package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.SiteDao;
import br.com.ttrans.samapp.model.Site;

@Repository
public class SiteDaoImpl implements SiteDao {

	@Autowired
	private SessionFactory session;

	@Override
	public void add(Site site, Authentication authentication) {
		site.setInsert(authentication.getName());
		session.getCurrentSession().save(site);

	}

	@Override
	public void edit(Site site, Authentication authentication) {
		site.setUpdate(authentication.getName());
		session.getCurrentSession().update(site);

	}

	@Override
	public void delete(Site site, Authentication authentication) {
		session.getCurrentSession().delete(site);

	}

	@Override
	public Site get(int id) {
		return (Site) session.getCurrentSession().get(Site.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Site> getAll() {
		
		return session.getCurrentSession().createCriteria(Site.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Site> loadData() {
		
		return session.getCurrentSession().createCriteria(Site.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

	@Override
	public Site findByName(String desc) {
		Criteria crit = session.getCurrentSession().createCriteria(Site.class);
		crit.add(Restrictions.eq("desc", desc));
		
		return (Site)crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> trackIt(int id){
		String cQuery;

		SQLQuery qQuery;
		
		cQuery = "WITH RECURSIVE link_tree AS (";
		cQuery += "	SELECT";
		cQuery += "		SIT_ID";
		cQuery += "		,SIT_PARENT_ID";
		cQuery += "		,SIT_DESCRIPTION";
		cQuery += "	FROM";
		cQuery += "		SITES";
		cQuery += "	WHERE";
		cQuery += "		SIT_ID = " + id;
		cQuery += "	UNION ALL"; 
		cQuery += "	SELECT";
		cQuery += "		c.SIT_ID";
		cQuery += "		,c.SIT_PARENT_ID";
		cQuery += "		,c.SIT_DESCRIPTION";
		cQuery += "	FROM";
		cQuery += "		SITES c";
		cQuery += "	JOIN";
		cQuery += "		link_tree p";
		cQuery += "	ON";
		cQuery += "	p.SIT_PARENT_ID = c.SIT_ID)";
		cQuery += "SELECT * FROM link_tree ORDER BY SIT_ID";

		qQuery = session.getCurrentSession().createSQLQuery(cQuery);
				
		return qQuery.list();
		
	}
}
