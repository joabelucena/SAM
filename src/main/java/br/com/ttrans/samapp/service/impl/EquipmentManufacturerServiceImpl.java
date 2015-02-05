package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.EquipmentManufacturerDao;
import br.com.ttrans.samapp.model.EquipmentManufacturer;
import br.com.ttrans.samapp.service.EquipmentManufacturerService;

@Repository
public class EquipmentManufacturerServiceImpl implements
		EquipmentManufacturerService {

	@Autowired
	private EquipmentManufacturerDao dao; 
	
	@Transactional
	public void add(EquipmentManufacturer manufacturer) {
		dao.add(manufacturer);
	}

	@Transactional
	public void edit(EquipmentManufacturer manufacturer) {
		dao.edit(manufacturer);
	}

	@Transactional
	public void delete(EquipmentManufacturer manufacturer) {
		dao.delete(manufacturer);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
