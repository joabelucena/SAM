package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.EquipmentDao;
import br.com.ttrans.samapp.library.IP;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.service.EquipmentService;

@Repository
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentDao dao;

	@Transactional
	public void add(Equipment equipment, Authentication authentication) {
		dao.add(equipment, authentication);
	}

	@Transactional
	public void edit(Equipment equipment, Authentication authentication) {
		dao.edit(equipment, authentication);
	}

	@Transactional
	public void delete(Equipment equipment, Authentication authentication) {
		dao.delete(equipment, authentication);
	}

	@Transactional
	public List<Equipment> loadData(){
		return dao.loadData();
	}
	
	@Transactional
	public List<Equipment> loadData(int start, int limit) {
		return dao.loadData(start, limit);
	}

	@Transactional
	public Equipment get(String id) {
		return dao.get(id);
	}
	
	@Transactional
	public Equipment get(IP ip) {
		return dao.get(ip);
	}

}
