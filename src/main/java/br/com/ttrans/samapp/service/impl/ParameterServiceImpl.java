package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.ParameterDao;
import br.com.ttrans.samapp.model.Parameters;
import br.com.ttrans.samapp.service.ParameterService;

@Repository
public class ParameterServiceImpl implements ParameterService {
	
	@Autowired
	private ParameterDao parameterDao;
	
	@Transactional
	public void add(Parameters parameter) {
		parameterDao.add(parameter);
	}

	@Transactional
	public void edit(Parameters parameter) {
		parameterDao.edit(parameter);
	}

	@Transactional
	public void delete(int parId) {
		parameterDao.delete(parId);
	}

	@Transactional
	public Parameters getParameter(int parId) {
		return parameterDao.getParameter(parId);
	}

	@Transactional
	public List getAllParameter() {
		return parameterDao.getAllParameter();
	}

}
