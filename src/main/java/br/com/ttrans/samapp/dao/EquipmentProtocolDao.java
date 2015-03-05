package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.EquipmentProtocol;

@SuppressWarnings("rawtypes")
public interface EquipmentProtocolDao {
	public void add(EquipmentProtocol protocol, Authentication authentication);
	public void edit(EquipmentProtocol protocol, Authentication authentication);
	public void delete(EquipmentProtocol protocol, Authentication authentication);
	public List loadData();
}
