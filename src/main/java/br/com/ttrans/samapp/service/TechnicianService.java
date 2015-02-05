package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.Technician;

public interface TechnicianService {
	public void add(Technician technician);
	public void edit(Technician technician);
	public void delete(Technician technician);
	public List loadData();
}
