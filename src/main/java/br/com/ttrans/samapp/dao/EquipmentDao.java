package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Equipment;

public interface EquipmentDao {
	public void add(Equipment equipment, Authentication authentication);
	public void edit(Equipment equipment, Authentication authentication);
	public void delete(Equipment equipment, Authentication authentication);
	public List loadData();
	public String getOidByIp(String ip);
	public Equipment get(String id);
}