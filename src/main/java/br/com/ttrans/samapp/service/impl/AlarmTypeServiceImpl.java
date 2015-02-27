package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.AlarmTypeDao;
import br.com.ttrans.samapp.model.AlarmType;
import br.com.ttrans.samapp.service.AlarmTypeService;

@Repository
public class AlarmTypeServiceImpl implements AlarmTypeService {

	@Autowired
	private AlarmTypeDao dao;
	
	@Transactional
	public void add(AlarmType type, Authentication authentication) {
		dao.add(type, authentication);
	}

	@Transactional
	public void edit(AlarmType type, Authentication authentication) {
		dao.edit(type, authentication);
	}

	@Transactional
	public void delete(AlarmType type, Authentication authentication) {
		dao.delete(type, authentication);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
