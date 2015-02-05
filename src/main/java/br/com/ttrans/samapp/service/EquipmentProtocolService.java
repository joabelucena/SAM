package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.EquipmentProtocol;

public interface EquipmentProtocolService {
	public void add(EquipmentProtocol protocol);
	public void edit(EquipmentProtocol protocol);
	public void delete(EquipmentProtocol protocol);
	public List loadData();
}
