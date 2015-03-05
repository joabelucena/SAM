package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.EquipmentProtocol;

@SuppressWarnings("rawtypes")
public interface EquipmentProtocolService {
	public void add(EquipmentProtocol protocol, Authentication authentication);
	public void edit(EquipmentProtocol protocol, Authentication authentication);
	public void delete(EquipmentProtocol protocol, Authentication authentication);
	public List loadData();
}
