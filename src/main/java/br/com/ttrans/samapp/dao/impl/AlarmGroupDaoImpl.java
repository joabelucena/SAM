package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.AlarmGroupDao;
import br.com.ttrans.samapp.model.AlarmGroup;

@Repository
public class AlarmGroupDaoImpl implements AlarmGroupDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(AlarmGroup group, Authentication authentication) {
		group.setInsert(authentication.getName());
		session.getCurrentSession().save(group);

	}

	@Override
	public void edit(AlarmGroup group, Authentication authentication) {
		group.setUpdate(authentication.getName());
		session.getCurrentSession().update(group);

	}

	@Override
	public void delete(AlarmGroup group, Authentication authentication) {
		session.getCurrentSession().delete(group);

	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(AlarmGroup.class);
				
		return crit.list();
	}

}
