package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public void add(Counter counter, Authentication authentication) {
		dao.add(counter, authentication);
	}

	@Transactional
	public void edit(Counter counter, Authentication authentication) {
		dao.edit(counter, authentication);
	}

	@Transactional
	public void delete(Counter counter, Authentication authentication) {
		dao.delete(counter, authentication);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
