package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.Technician;

public interface TechnicianDao {
	public void add(Technician technician);
	public void edit(Technician technician);
	public void delete(Technician technician);
	public List loadData();
}
