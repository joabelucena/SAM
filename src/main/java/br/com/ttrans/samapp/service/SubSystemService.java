package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.SubSystem;

public interface SubSystemService {
	public void add(SubSystem system, Authentication authentication);
	public void edit(SubSystem system, Authentication authentication);
	public void delete(SubSystem system, Authentication authentication);
	public List<SubSystem> loadData();
}
