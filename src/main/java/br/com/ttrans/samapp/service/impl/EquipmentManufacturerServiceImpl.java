package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public void add(EquipmentManufacturer manufacturer, Authentication authentication) {
		dao.add(manufacturer, authentication);
	}

	@Transactional
	public void edit(EquipmentManufacturer manufacturer, Authentication authentication) {
		dao.edit(manufacturer, authentication);
	}

	@Transactional
	public void delete(EquipmentManufacturer manufacturer, Authentication authentication) {
		dao.delete(manufacturer, authentication);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
