package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.EquipmentModel;

public interface EquipmentModelService {
	public void add(EquipmentModel model, Authentication authentication);
	public void edit(EquipmentModel model, Authentication authentication);
	public void delete(EquipmentModel model, Authentication authentication);
	public EquipmentModel get(int id);
	public List<EquipmentModel> loadData();
}
