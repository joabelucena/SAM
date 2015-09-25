package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.SiteTypeDao;
import br.com.ttrans.samapp.model.SiteType;

@Repository
public class SiteTypeDaoImpl implements SiteTypeDao {
	
	@Autowired
	private SessionFactory session;

	@Override
	public void add(SiteType siteType, Authentication authentication) {
		siteType.setInsert(authentication.getName());
		session.getCurrentSession().save(siteType);
	}

	@Override
	public void edit(SiteType siteType, Authentication authentication) {
		siteType.setUpdate(authentication.getName());
		session.getCurrentSession().update(siteType);
	}

	@Override
	public void delete(SiteType siteType, Authentication authentication) {
		session.getCurrentSession().delete(siteType);
	}

	@Override
	public SiteType get(int id) {
		return (SiteType)session.getCurrentSession().get(SiteType.class, id);
	}
	
	@Override
	public SiteType findByName(String styDesc) {
		Criteria crit = session.getCurrentSession().createCriteria(SiteType.class);
		crit.add(Restrictions.eq("desc", styDesc));
		
		return (SiteType)crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SiteType> getAll() {
		
		return session.getCurrentSession().createCriteria(SiteType.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SiteType> loadData() {
		
		return session.getCurrentSession().createCriteria(SiteType.class).list();		
	}
}
