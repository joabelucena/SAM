package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.OperationalState;

public interface OperationalStateService {
	public void add(OperationalState state);
	public void edit(OperationalState state);
	public void delete(OperationalState state);
	public List loadData();
}
