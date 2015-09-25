package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.ServiceOrderLog;


public interface ServiceOrderLogDao {
	public void add(ServiceOrderLog log, Authentication authentication);
	public void edit(ServiceOrderLog log, Authentication authentication);
	public void delete(ServiceOrderLog log, Authentication authentication);
	public List<ServiceOrderLog> loadData();
}
