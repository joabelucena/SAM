package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.StatusRule;

public interface StatusRuleService {
	public void add(StatusRule rule);
	public void edit(StatusRule rule);
	public void delete(StatusRule rule);
	public StatusRule getRule(int id);
	public List loadData();
}