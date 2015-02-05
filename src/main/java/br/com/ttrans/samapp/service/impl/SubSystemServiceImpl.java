package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void add(SubSystem system) {
		dao.add(system);
	}

	@Transactional
	public void edit(SubSystem system) {
		dao.edit(system);
	}

	@Transactional
	public void delete(SubSystem system) {
		dao.delete(system);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
