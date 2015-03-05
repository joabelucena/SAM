package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Equipment;

@SuppressWarnings("rawtypes")
public interface EquipmentService {
	public void add(Equipment equipment, Authentication authentication);
	public void edit(Equipment equipment, Authentication authentication);
	public void delete(Equipment equipment, Authentication authentication);
	public List loadData();
	public String getOidByIp(String ip);
	public Equipment get(String id);
}
