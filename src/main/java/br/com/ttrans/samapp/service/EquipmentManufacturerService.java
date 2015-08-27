package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.EquipmentManufacturer;

public interface EquipmentManufacturerService {
	public void add(EquipmentManufacturer manufacturer, Authentication authentication);
	public void edit(EquipmentManufacturer manufacturer, Authentication authentication);
	public void delete(EquipmentManufacturer manufacturer, Authentication authentication);
	public List<EquipmentManufacturer> loadData();
}
