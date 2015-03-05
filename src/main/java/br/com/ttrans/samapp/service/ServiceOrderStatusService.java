package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.ServiceOrderStatus;

@SuppressWarnings("rawtypes")
public interface ServiceOrderStatusService {
	public void add(ServiceOrderStatus status, Authentication authentication);
	public void edit(ServiceOrderStatus status, Authentication authentication);
	public void delete(ServiceOrderStatus status, Authentication authentication);
	public ServiceOrderStatus get(int id);
	public ServiceOrderStatus findByName(String desc);
	public List loadData();
}
