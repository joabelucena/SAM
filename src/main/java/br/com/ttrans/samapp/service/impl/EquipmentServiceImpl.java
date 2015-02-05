package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.EquipmentDao;
import br.com.ttrans.samapp.model.Equipment;
import br.com.ttrans.samapp.service.EquipmentService;

@Repository
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentDao dao;

	@Transactional
	public void add(Equipment equipment) {
		dao.add(equipment);
	}

	@Transactional
	public void edit(Equipment equipment) {
		dao.edit(equipment);
	}

	@Transactional
	public void delete(Equipment equipment) {
		dao.delete(equipment);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

	@Transactional
	public String getOidByIp(String ip) {
		return dao.getOidByIp(ip);
	}

	@Transactional
	public Equipment getEquipment(String id) {
		return dao.getEquipment(id);
	}

}
