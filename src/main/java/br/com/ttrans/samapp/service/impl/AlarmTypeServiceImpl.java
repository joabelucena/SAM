package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void add(AlarmType type) {
		dao.add(type);

	}

	@Transactional
	public void edit(AlarmType type) {
		dao.edit(type);

	}

	@Transactional
	public void delete(AlarmType type) {
		dao.delete(type);

	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
