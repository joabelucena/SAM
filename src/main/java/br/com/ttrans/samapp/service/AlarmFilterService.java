package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.AlarmFilter;

@SuppressWarnings("rawtypes")
public interface AlarmFilterService {
	public void add(AlarmFilter filter, Authentication authentication);
	public void edit(AlarmFilter filter, Authentication authentication);
	public void delete(AlarmFilter filter, Authentication authentication);
	public List loadData();
}
