package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.SeverityLevelDao;
import br.com.ttrans.samapp.model.SeverityLevel;

@Repository
public class SeverityLevelDaoImpl implements SeverityLevelDao {

	@Autowired
	private SessionFactory session;

	@Override
	public void add(SeverityLevel severity, Authentication authentication) {
		severity.setInsert(authentication.getName());
		session.getCurrentSession().save(severity);

	}

	@Override
	public void edit(SeverityLevel severity, Authentication authentication) {
		severity.setUpdate(authentication.getName());
		session.getCurrentSession().update(severity);

	}

	@Override
	public void delete(SeverityLevel severity, Authentication authentication) {
		session.getCurrentSession().delete(severity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SeverityLevel> loadData() {

		return session.getCurrentSession().createCriteria(SeverityLevel.class).list();
		
	}

}
