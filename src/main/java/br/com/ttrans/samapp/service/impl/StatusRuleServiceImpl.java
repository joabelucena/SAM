package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.StatusRuleDao;
import br.com.ttrans.samapp.model.Role;
import br.com.ttrans.samapp.model.ServiceOrderStatus;
import br.com.ttrans.samapp.model.StatusRule;
import br.com.ttrans.samapp.service.StatusRuleService;

@Repository
public class StatusRuleServiceImpl implements StatusRuleService {

	@Autowired
	private StatusRuleDao dao;
	
	@Transactional
	public void add(StatusRule rule, Authentication authentication) {
		dao.add(rule, authentication);
	}

	@Transactional
	public void edit(StatusRule rule, Authentication authentication) {
		dao.edit(rule, authentication);
	}

	@Transactional
	public void delete(StatusRule rule, Authentication authentication) {
		dao.delete(rule, authentication);
	}
	
	@Transactional
	public StatusRule get(int id) {
		return dao.get(id);
	}
	
	@Transactional
	public List<ServiceOrderStatus> getAllowedStatus(Role role, ServiceOrderStatus curstatus){
		return dao.getAllowedStatus(role, curstatus);
	}
	
	@Transactional
	public List<ServiceOrderStatus> getStatusByRole(Role role){
		return dao.getStatusByRole(role);
	}
	
	@Transactional
	public List<StatusRule> loadData() {
		return dao.loadData();
	}
}
