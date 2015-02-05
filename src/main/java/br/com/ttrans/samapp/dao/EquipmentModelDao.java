package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.EquipmentModel;

public interface EquipmentModelDao {
	public void add(EquipmentModel model);
	public void edit(EquipmentModel model);
	public void delete(EquipmentModel model);
	public List loadData();
}
