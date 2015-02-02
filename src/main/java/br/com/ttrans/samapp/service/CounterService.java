package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.Counter;

public interface CounterService {
	public void add(Counter counter);
	public void edit(Counter counter);
	public void delete(Counter counter);
	public List loadData();
}
