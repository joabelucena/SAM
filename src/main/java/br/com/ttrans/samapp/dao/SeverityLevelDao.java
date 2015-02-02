package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.SeverityLevel;

public interface SeverityLevelDao {
	public void add(SeverityLevel severity);
	public void edit(SeverityLevel severity);
	public void delete(SeverityLevel severity);
	public List loadData();
}
