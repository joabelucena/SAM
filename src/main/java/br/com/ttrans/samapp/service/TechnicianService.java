package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Technician;

public interface TechnicianService {
	public void add(Technician technician, Authentication authentication);
	public void edit(Technician technician, Authentication authentication);
	public void delete(Technician technician, Authentication authentication);
	public Technician get(String id);
	public List loadData();
}
