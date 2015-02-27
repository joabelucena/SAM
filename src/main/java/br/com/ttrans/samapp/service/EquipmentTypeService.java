package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.EquipmentType;

public interface EquipmentTypeService {
	public void add(EquipmentType type, Authentication authentication);
	public void edit(EquipmentType type, Authentication authentication);
	public void delete(EquipmentType type, Authentication authentication);
	public List loadData();
}
