package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.EquipmentProtocol;

public interface EquipmentProtocolDao {
	public void add(EquipmentProtocol protocol);
	public void edit(EquipmentProtocol protocol);
	public void delete(EquipmentProtocol protocol);
	public List loadData();
}
