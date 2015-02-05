package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.SiteTypeDao;
import br.com.ttrans.samapp.model.SiteType;

@Repository
public class SiteTypeDaoImpl implements SiteTypeDao {
	
	@Autowired
	private SessionFactory session;

	@Override
	public void add(SiteType siteType) {
		session.getCurrentSession().save(siteType);
	}

	@Override
	public void edit(SiteType siteType) {
		session.getCurrentSession().update(siteType);
	}

	@Override
	public void delete(int styId) {
		session.getCurrentSession().delete(getSiteType(styId));
	}

	@Override
	public SiteType getSiteType(int styId) {
		return (SiteType)session.getCurrentSession().get(SiteType.class, styId);
	}
	
	@Override
	public SiteType findByName(String styDesc) {
		Criteria crit = session.getCurrentSession().createCriteria(SiteType.class);
		crit.add(Restrictions.eq("sty_description", styDesc));
		crit.add(Restrictions.ne("deleted", "*"));
		
		return (SiteType)crit.uniqueResult();
	}

	@Override
	public List getAllSiteType() {
		return session.getCurrentSession().createQuery("from SitesType where deleted <> '*'").list();
	}

	@Override
	public List loadData() {
		Criteria crit = session.getCurrentSession().createCriteria(SiteType.class,"types");
				
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("types.sty_id"));
		projList.add(Projections.property("types.sty_description"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("types.deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}
}
