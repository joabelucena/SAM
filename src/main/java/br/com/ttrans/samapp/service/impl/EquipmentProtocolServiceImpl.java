package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public void add(EquipmentProtocol protocol, Authentication authentication) {
		dao.add(protocol, authentication);
	}

	@Transactional
	public void edit(EquipmentProtocol protocol, Authentication authentication) {
		dao.edit(protocol, authentication);
	}

	@Transactional
	public void delete(EquipmentProtocol protocol, Authentication authentication) {
		dao.delete(protocol, authentication);
	}

	@Transactional
	public List<EquipmentProtocol> loadData() {
		return dao.loadData();
	}

}
