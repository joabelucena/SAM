package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.Counter;

public interface CounterDao {
	public void add(Counter counter);
	public void edit(Counter counter);
	public void delete(Counter counter);
	public List loadData();
}
