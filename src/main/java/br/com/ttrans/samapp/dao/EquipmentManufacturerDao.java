package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.EquipmentManufacturer;

@SuppressWarnings("rawtypes")
public interface EquipmentManufacturerDao {
	public void add(EquipmentManufacturer manufacturer, Authentication authentication);
	public void edit(EquipmentManufacturer manufacturer, Authentication authentication);
	public void delete(EquipmentManufacturer manufacturer, Authentication authentication);
	public List loadData();
}
