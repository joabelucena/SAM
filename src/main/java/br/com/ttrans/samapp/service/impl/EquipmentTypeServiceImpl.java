package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.EquipmentTypeDao;
import br.com.ttrans.samapp.model.EquipmentType;
import br.com.ttrans.samapp.service.EquipmentTypeService;

@Repository
public class EquipmentTypeServiceImpl implements EquipmentTypeService {
	
	@Autowired
	private EquipmentTypeDao dao;

	@Transactional
	public void add(EquipmentType type, Authentication authentication) {
		dao.add(type, authentication);
	}

	@Transactional
	public void edit(EquipmentType type, Authentication authentication) {
		dao.edit(type, authentication);
	}

	@Transactional
	public void delete(EquipmentType type, Authentication authentication) {
		dao.delete(type, authentication);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
