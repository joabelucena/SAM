package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.SubSystem;

public interface SubSystemService {
	public void add(SubSystem system);
	public void edit(SubSystem system);
	public void delete(SubSystem system);
	public List loadData();
}
