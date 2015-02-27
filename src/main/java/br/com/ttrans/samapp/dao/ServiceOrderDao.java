package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.ServiceOrder;

public interface ServiceOrderDao {
	public void add(ServiceOrder serviceorder, Authentication authentication);
	public void edit(ServiceOrder serviceorder, Authentication authentication);
	public void delete(ServiceOrder serviceorder, Authentication authentication);
	public ServiceOrder get(int id);
	public List loadData();
}
