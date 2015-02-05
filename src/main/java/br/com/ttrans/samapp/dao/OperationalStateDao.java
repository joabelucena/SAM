package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.OperationalState;

public interface OperationalStateDao {
	public void add(OperationalState state);
	public void edit(OperationalState state);
	public void delete(OperationalState state);
	public List loadData();
}
