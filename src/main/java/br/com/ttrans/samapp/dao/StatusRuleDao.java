package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.StatusRule;

public interface StatusRuleDao {
	public void add(StatusRule rule, Authentication authentication);
	public void edit(StatusRule rule, Authentication authentication);
	public void delete(StatusRule rule, Authentication authentication);
	public StatusRule get(int id);
	public List loadData();
}
