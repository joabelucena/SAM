package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.EquipmentType;

public interface EquipmentTypeService {
	public void add(EquipmentType type);
	public void edit(EquipmentType type);
	public void delete(EquipmentType type);
	public List loadData();
}
