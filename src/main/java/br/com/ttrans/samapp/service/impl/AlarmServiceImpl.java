package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.AlarmDao;
import br.com.ttrans.samapp.model.Alarm;
import br.com.ttrans.samapp.service.AlarmService;

@Repository
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	private AlarmDao dao;

	@Transactional
	public void add(Alarm alarm, Authentication authentication) {
		dao.add(alarm, authentication);
	}

	@Transactional
	public void edit(Alarm alarm, Authentication authentication) {
		dao.edit(alarm, authentication);
	}

	@Transactional
	public void delete(Alarm alarm, Authentication authentication) {
		dao.delete(alarm, authentication);
	}
	
	@Transactional
	public Alarm get(String id){
		return dao.get(id);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}
}
