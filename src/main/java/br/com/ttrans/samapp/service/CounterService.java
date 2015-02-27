package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Counter;

public interface CounterService {
	public void add(Counter counter, Authentication authentication);
	public void edit(Counter counter, Authentication authentication);
	public void delete(Counter counter, Authentication authentication);
	public List loadData();
}
