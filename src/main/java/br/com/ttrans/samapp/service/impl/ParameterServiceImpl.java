package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public void add(Parameters parameter, Authentication authentication) {
		parameterDao.add(parameter, authentication);
	}

	@Transactional
	public void edit(Parameters parameter, Authentication authentication) {
		parameterDao.edit(parameter, authentication);
	}

	@Transactional
	public void delete(Parameters parameter, Authentication authentication) {
		parameterDao.delete(parameter, authentication);
	}

	@Transactional
	public Parameters get(int id) {
		return parameterDao.get(id);
	}

	@Transactional
	public List getAll() {
		return parameterDao.getAll();
	}

}
