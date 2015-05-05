package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.ParameterDao;
import br.com.ttrans.samapp.model.Parameter;
import br.com.ttrans.samapp.service.ParameterService;

@Repository
public class ParameterServiceImpl implements ParameterService {
	
	@Autowired
	private ParameterDao parameterDao;
	
	@Transactional
	public void add(Parameter parameter, Authentication authentication) {
		parameterDao.add(parameter, authentication);
	}

	@Transactional
	public void edit(Parameter parameter, Authentication authentication) {
		parameterDao.edit(parameter, authentication);
	}

	@Transactional
	public void delete(Parameter parameter, Authentication authentication) {
		parameterDao.delete(parameter, authentication);
	}

	@Transactional
	public Parameter get(int id) {
		return parameterDao.get(id);
	}

	@Transactional
	public List<Parameter>  getAll() {
		return parameterDao.getAll();
	}

}
