package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.EquipmentManufacturer;

public interface EquipmentManufacturerService {
	public void add(EquipmentManufacturer manufacturer);
	public void edit(EquipmentManufacturer manufacturer);
	public void delete(EquipmentManufacturer manufacturer);
	public List loadData();
}
