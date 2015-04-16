package br.com.ttrans.samapp.dao;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.Role;
import br.com.ttrans.samapp.model.ServiceOrderStatus;
import br.com.ttrans.samapp.model.StatusRule;

@SuppressWarnings("rawtypes")
public interface StatusRuleDao {
	public void add(StatusRule rule, Authentication authentication);
	public void edit(StatusRule rule, Authentication authentication);
	public void delete(StatusRule rule, Authentication authentication);
	public StatusRule get(int id);
	public List<String> getAllowedStatus(Role role, ServiceOrderStatus curstatus);
	public List loadData();
}
