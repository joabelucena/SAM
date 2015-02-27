package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.AlarmGroup;

public interface AlarmGroupService {
	public void add(AlarmGroup group, Authentication authentication);
	public void edit(AlarmGroup group, Authentication authentication);
	public void delete(AlarmGroup group, Authentication authentication);
	public List loadData();
}
