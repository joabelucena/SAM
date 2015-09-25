package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.SubSystemDao;
import br.com.ttrans.samapp.model.SubSystem;
import br.com.ttrans.samapp.service.SubSystemService;

@Repository
public class SubSystemServiceImpl implements SubSystemService {
	
	@Autowired
	private SubSystemDao dao;

	@Transactional
	public void add(SubSystem system, Authentication authentication) {
		dao.add(system, authentication);
	}

	@Transactional
	public void edit(SubSystem system, Authentication authentication) {
		dao.edit(system, authentication);
	}

	@Transactional
	public void delete(SubSystem system, Authentication authentication) {
		dao.delete(system, authentication);
	}

	@Transactional
	public List<SubSystem> loadData() {
		return dao.loadData();
	}

}
