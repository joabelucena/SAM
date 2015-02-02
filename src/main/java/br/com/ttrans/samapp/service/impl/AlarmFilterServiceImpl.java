package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.AlarmFilterDao;
import br.com.ttrans.samapp.model.AlarmFilter;
import br.com.ttrans.samapp.service.AlarmFilterService;

@Repository
public class AlarmFilterServiceImpl implements AlarmFilterService {

	@Autowired
	private AlarmFilterDao dao;

	@Transactional
	public void add(AlarmFilter filter) {
		dao.add(filter);

	}

	@Transactional
	public void edit(AlarmFilter filter) {
		dao.edit(filter);

	}

	@Transactional
	public void delete(AlarmFilter filter) {
		dao.delete(filter);

	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
