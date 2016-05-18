package br.com.ttrans.samapp.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.EquipmentModelDao;
import br.com.ttrans.samapp.model.EquipmentModel;
import br.com.ttrans.samapp.model.EquipmentOID;
import br.com.ttrans.samapp.service.EquipmentModelService;

@Repository
public class EquipmentModelServiceImpl implements EquipmentModelService {

	@Autowired
	private EquipmentModelDao dao;

	@Transactional
	public void add(EquipmentModel model, Authentication authentication) {

		Iterator<EquipmentOID> oids = model.getOIDs().iterator();

		// Sets insert user and relational conditions
		while (oids.hasNext()) {
			EquipmentOID oid = oids.next();
			oid.setInsert(authentication.getName());
		}

		model.setInsert(authentication.getName());

		dao.add(model, authentication);
	}

	@Transactional
	public void edit(EquipmentModel model, Authentication authentication) {

		Iterator<EquipmentOID> oids = model.getOIDs().iterator();

		// Sets update user and relational conditions
		while (oids.hasNext()) {
			EquipmentOID oid = oids.next();

			// Eh registro novo
			if (oid.getInsert() == null) {
				oid.setInsert(authentication.getName());
			} else {
				oid.setUpdate(authentication.getName());
			}
		}

		model.setUpdate(authentication.getName());

		dao.edit(model, authentication);
	}

	@Transactional
	public void delete(EquipmentModel model, Authentication authentication) {
		dao.delete(model, authentication);
	}

	@Transactional
	public EquipmentModel get(int id) {
		return dao.get(id);
	}

	@Transactional
	public List<EquipmentModel> loadData() {
		return dao.loadData();
	}

}
