package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.ServiceOrderJob;

@SuppressWarnings("rawtypes")
public interface ServiceOrderJobDao {
	public void add(ServiceOrderJob service, Authentication authentication);
	public void edit(ServiceOrderJob service, Authentication authentication);
	public void delete(ServiceOrderJob service, Authentication authentication);
	public ServiceOrderJob get(String id);
	public List loadData();
}
