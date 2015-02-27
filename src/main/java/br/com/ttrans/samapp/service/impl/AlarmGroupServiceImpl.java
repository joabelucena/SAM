package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.AlarmGroupDao;
import br.com.ttrans.samapp.model.AlarmGroup;
import br.com.ttrans.samapp.service.AlarmGroupService;

@Repository
public class AlarmGroupServiceImpl implements AlarmGroupService {

	@Autowired
	private AlarmGroupDao dao;
	
	@Transactional
	public void add(AlarmGroup group, Authentication authentication) {
		dao.add(group, authentication);
	}

	@Transactional
	public void edit(AlarmGroup group, Authentication authentication) {
		dao.edit(group, authentication);
	}

	@Transactional
	public void delete(AlarmGroup group, Authentication authentication) {
		dao.delete(group, authentication);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
