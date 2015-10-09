package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public void add(EquipmentModel model, Authentication authentication) {
		dao.add(model, authentication);
	}

	@Transactional
	public void edit(EquipmentModel model, Authentication authentication) {
		dao.edit(model, authentication);
	}

	@Transactional
	public void delete(EquipmentModel model, Authentication authentication) {
		dao.delete(model, authentication);
	}
	
	@Transactional
	public EquipmentModel get(int id){
		return dao.get(id);
	}

	@Transactional
	public List<EquipmentModel> loadData() {
		return dao.loadData();
	}

}
