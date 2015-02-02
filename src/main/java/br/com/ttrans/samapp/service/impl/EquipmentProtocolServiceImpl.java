package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.EquipmentProtocolDao;
import br.com.ttrans.samapp.model.EquipmentProtocol;
import br.com.ttrans.samapp.service.EquipmentProtocolService;

@Repository
public class EquipmentProtocolServiceImpl implements EquipmentProtocolService {
	
	@Autowired
	private EquipmentProtocolDao dao;

	@Transactional
	public void add(EquipmentProtocol protocol) {
		dao.add(protocol);
	}

	@Transactional
	public void edit(EquipmentProtocol protocol) {
		dao.edit(protocol);
	}

	@Transactional
	public void delete(EquipmentProtocol protocol) {
		dao.delete(protocol);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
