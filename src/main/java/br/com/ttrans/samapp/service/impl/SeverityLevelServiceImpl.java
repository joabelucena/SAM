package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.SeverityLevelDao;
import br.com.ttrans.samapp.model.SeverityLevel;
import br.com.ttrans.samapp.service.SeverityLevelService;

@Repository
public class SeverityLevelServiceImpl implements SeverityLevelService {

	@Autowired
	private SeverityLevelDao dao;

	@Transactional
	public void add(SeverityLevel severity, Authentication authentication) {
		dao.add(severity, authentication);
	}

	@Transactional
	public void edit(SeverityLevel severity, Authentication authentication) {
		dao.edit(severity, authentication);
	}

	@Transactional
	public void delete(SeverityLevel severity, Authentication authentication) {
		dao.delete(severity, authentication);
	}

	@Transactional
	public List<SeverityLevel> loadData() {
		return dao.loadData();
	}

}
