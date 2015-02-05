package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.EquipmentModelDao;
import br.com.ttrans.samapp.model.EquipmentModel;
import br.com.ttrans.samapp.service.EquipmentModelService;

@Repository
public class EquipmentModelServiceImpl implements EquipmentModelService {
	
	@Autowired
	private EquipmentModelDao dao;

	@Transactional
	public void add(EquipmentModel model) {
		dao.add(model);
	}

	@Transactional
	public void edit(EquipmentModel model) {
		dao.edit(model);
	}

	@Transactional
	public void delete(EquipmentModel model) {
		dao.delete(model);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
