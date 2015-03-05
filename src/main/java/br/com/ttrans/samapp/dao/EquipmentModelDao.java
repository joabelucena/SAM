package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.EquipmentModel;

@SuppressWarnings("rawtypes")
public interface EquipmentModelDao {
	public void add(EquipmentModel model, Authentication authentication);
	public void edit(EquipmentModel model, Authentication authentication);
	public void delete(EquipmentModel model, Authentication authentication);
	public List loadData();
}