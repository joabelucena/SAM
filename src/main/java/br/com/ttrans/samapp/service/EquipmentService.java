package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.Equipment;

public interface EquipmentService {
	public void add(Equipment equipment);
	public void edit(Equipment equipment);
	public void delete(Equipment equipment);
	public List loadData();
	public String getOidByIp(String ip);
	public Equipment getEquipment(String id);
}
