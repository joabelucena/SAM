package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.ServiceOrderType;

public interface ServiceOrderTypeService {
	public void add(ServiceOrderType type, Authentication authentication);
	public void edit(ServiceOrderType type, Authentication authentication);
	public void delete(ServiceOrderType type, Authentication authentication);
	public ServiceOrderType findByName(String desc);
	public List loadData();
}
