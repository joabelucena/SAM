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

import br.com.ttrans.samapp.dao.SiteDao;
import br.com.ttrans.samapp.model.Site;

@SuppressWarnings("rawtypes")
@Repository
public class SiteDaoImpl implements SiteDao {

	@Autowired
	private SessionFactory session;

	@Override
	public void add(Site site, Authentication authentication) {
		site.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(site);

	}

	@Override
	public void edit(Site site, Authentication authentication) {
		site.setUsr_update(authentication.getName());
		session.getCurrentSession().update(site);

	}

	@Override
	public void delete(Site site, Authentication authentication) {
		site.setUsr_update(authentication.getName());
		site.setDeleted("*");
		session.getCurrentSession().update(site);

	}

	@Override
	public Site get(int id) {
		return (Site) session.getCurrentSession().get(Site.class, id);
	}

	@Override
	public List getAll() {
		return session.getCurrentSession()
				.createQuery("from Sites where deleted <> '*'").list();
	}

	@Override
	public List loadData() {
		Criteria crit = session.getCurrentSession().createCriteria(Site.class,"sites");
		
		
		crit.createAlias("sites.type","sites_type",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("sites.parent","sites_parent",CriteriaSpecification.LEFT_JOIN);
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("sites.sit_id"));
		projList.add(Projections.property("sites.sit_description"));
		projList.add(Projections.property("sites.sit_shortname"));
		projList.add(Projections.property("sites_parent.sit_description"));
		projList.add(Projections.property("sites_type.sty_description"));
		
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("sites_type.deleted", "*"));
		crit.add(Restrictions.ne("sites.deleted", "*"));
		List resultsList = crit.list();
		
		return resultsList;
		
	}

	@Override
	public Site findByName(String desc) {
		Criteria crit = session.getCurrentSession().createCriteria(Site.class);
		crit.add(Restrictions.eq("sit_description", desc));
		crit.add(Restrictions.ne("deleted", "*"));
		
		return (Site)crit.uniqueResult();
	}
}
