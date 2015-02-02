package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void add(SeverityLevel severity) {
		dao.add(severity);

	}

	@Transactional
	public void edit(SeverityLevel severity) {
		dao.edit(severity);

	}

	@Transactional
	public void delete(SeverityLevel severity) {
		dao.delete(severity);

	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
