package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.SubSystem;

public interface SubSystemDao {
	public void add(SubSystem system);
	public void edit(SubSystem system);
	public void delete(SubSystem system);
	public List loadData();
}
