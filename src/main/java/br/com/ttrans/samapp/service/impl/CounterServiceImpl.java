package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.CounterDao;
import br.com.ttrans.samapp.model.Counter;
import br.com.ttrans.samapp.service.CounterService;

@Repository
public class CounterServiceImpl implements CounterService {
	
	@Autowired
	private CounterDao dao;

	@Transactional
	public void add(Counter counter) {
		dao.add(counter);
	}

	@Transactional
	public void edit(Counter counter) {
		dao.edit(counter);
	}

	@Transactional
	public void delete(Counter counter) {
		dao.delete(counter);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
