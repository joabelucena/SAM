package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void add(EquipmentType type) {
		dao.add(type);
	}

	@Transactional
	public void edit(EquipmentType type) {
		dao.edit(type);
	}

	@Transactional
	public void delete(EquipmentType type) {
		dao.delete(type);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
