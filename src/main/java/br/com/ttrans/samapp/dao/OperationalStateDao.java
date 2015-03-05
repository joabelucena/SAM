package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.OperationalState;

@SuppressWarnings("rawtypes")
public interface OperationalStateDao {
	public void add(OperationalState state, Authentication authentication);
	public void edit(OperationalState state, Authentication authentication);
	public void delete(OperationalState state, Authentication authentication);
	public OperationalState get(String id);
	public List loadData();
}
