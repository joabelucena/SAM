package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.library.IP;
import br.com.ttrans.samapp.model.Equipment;

public interface EquipmentService {
	public void add(Equipment equipment, Authentication authentication);
	public void edit(Equipment equipment, Authentication authentication);
	public void delete(Equipment equipment, Authentication authentication);
	public List<Equipment> loadData();
	public List<Equipment> loadData(int start, int limit);
	public Equipment get(String id);
	public Equipment get(IP ip);
}
