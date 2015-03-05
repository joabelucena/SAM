package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.ServiceOrderLog;

@SuppressWarnings("rawtypes")
public interface ServiceOrderLogService {
	public void add(ServiceOrderLog log, Authentication authentication);
	public void edit(ServiceOrderLog log, Authentication authentication);
	public void delete(ServiceOrderLog log, Authentication authentication);
	public List loadData();
}
