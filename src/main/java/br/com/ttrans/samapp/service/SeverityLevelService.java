package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.SeverityLevel;

public interface SeverityLevelService {
	public void add(SeverityLevel severity);
	public void edit(SeverityLevel severity);
	public void delete(SeverityLevel severity);
	public List loadData();
}
