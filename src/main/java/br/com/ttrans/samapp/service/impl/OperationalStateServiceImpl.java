package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.OperationalStateDao;
import br.com.ttrans.samapp.model.OperationalState;
import br.com.ttrans.samapp.service.OperationalStateService;

@Repository
public class OperationalStateServiceImpl implements OperationalStateService {
	
	@Autowired
	private OperationalStateDao dao;

	@Transactional
	public void add(OperationalState state) {
		dao.add(state);
	}

	@Transactional
	public void edit(OperationalState state) {
		dao.edit(state);
	}

	@Transactional
	public void delete(OperationalState state) {
		dao.delete(state);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
