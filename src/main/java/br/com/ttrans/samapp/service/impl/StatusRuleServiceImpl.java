package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.StatusRuleDao;
import br.com.ttrans.samapp.model.StatusRule;
import br.com.ttrans.samapp.service.StatusRuleService;

@Repository
public class StatusRuleServiceImpl implements StatusRuleService {

	@Autowired
	private StatusRuleDao dao;
	
	@Transactional
	public void add(StatusRule rule) {
		dao.add(rule);

	}

	@Transactional
	public void edit(StatusRule rule) {
		dao.edit(rule);

	}

	@Transactional
	public void delete(StatusRule rule) {
		dao.delete(rule);

	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

	@Override
	public StatusRule getRule(int id) {
		return dao.getRule(id);
	}

}
