package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void add(Alarm alarm) {
		dao.add(alarm);

	}

	@Transactional
	public void edit(Alarm alarm) {
		dao.edit(alarm);

	}

	@Transactional
	public void delete(Alarm alarm) {
		dao.delete(alarm);

	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
