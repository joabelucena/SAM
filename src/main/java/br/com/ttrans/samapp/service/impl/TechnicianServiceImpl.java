package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.TechnicianDao;
import br.com.ttrans.samapp.model.Technician;
import br.com.ttrans.samapp.service.TechnicianService;

@Repository
public class TechnicianServiceImpl implements TechnicianService {

	@Autowired
	private TechnicianDao dao;
	
	@Transactional
	public void add(Technician technician, Authentication authentication) {
		dao.add(technician, authentication);
	}

	@Transactional
	public void edit(Technician technician, Authentication authentication) {
		dao.edit(technician, authentication);
	}

	@Transactional
	public void delete(Technician technician, Authentication authentication) {
		dao.delete(technician, authentication);
	}
	
	@Transactional
	public Technician get(String id) {
		return dao.get(id); 
	}

	@Transactional
	public List<Technician> loadData() {
		return dao.loadData();
	}
}
