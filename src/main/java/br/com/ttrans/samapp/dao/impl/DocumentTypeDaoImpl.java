package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.DocumentTypeDao;
import br.com.ttrans.samapp.model.DocumentType;

@Repository
public class DocumentTypeDaoImpl implements DocumentTypeDao {
	
	@Autowired
	private SessionFactory session;

	@Override
	public void add(DocumentType type, Authentication authentication) {
		type.setInsert(authentication.getName());
		session.getCurrentSession().save(type);
	}

	@Override
	public void edit(DocumentType type, Authentication authentication) {
		type.setUpdate(authentication.getName());
		session.getCurrentSession().update(type);
	}

	@Override
	public void delete(DocumentType type, Authentication authentication) {
		session.getCurrentSession().delete(type);
	}

	@Override
	public List<DocumentType> loadData() {
		
		Criteria crit = session.getCurrentSession().createCriteria(DocumentType.class);
		
		return crit.list();
	}

}
