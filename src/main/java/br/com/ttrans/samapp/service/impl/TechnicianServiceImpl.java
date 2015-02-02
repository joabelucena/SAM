package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void add(Technician technician) {
		dao.add(technician);

	}

	@Transactional
	public void edit(Technician technician) {
		dao.edit(technician);

	}

	@Transactional
	public void delete(Technician technician) {
		dao.delete(technician);

	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
