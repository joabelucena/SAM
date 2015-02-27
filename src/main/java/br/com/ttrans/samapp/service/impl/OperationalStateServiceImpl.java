package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public void add(OperationalState state, Authentication authentication) {
		dao.add(state, authentication);
	}

	@Transactional
	public void edit(OperationalState state, Authentication authentication) {
		dao.edit(state, authentication);
	}

	@Transactional
	public void delete(OperationalState state, Authentication authentication) {
		dao.delete(state, authentication);
	}
	
	@Transactional
	public OperationalState get(String id) {
		return dao.get(id);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}
}
