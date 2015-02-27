package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.AlarmFilterDao;
import br.com.ttrans.samapp.model.AlarmFilter;
import br.com.ttrans.samapp.service.AlarmFilterService;

@Repository
public class AlarmFilterServiceImpl implements AlarmFilterService {

	@Autowired
	private AlarmFilterDao dao;

	@Transactional
	public void add(AlarmFilter filter, Authentication authentication) {
		dao.add(filter, authentication);
	}

	@Transactional
	public void edit(AlarmFilter filter, Authentication authentication) {
		dao.edit(filter, authentication);
	}

	@Transactional
	public void delete(AlarmFilter filter, Authentication authentication) {
		dao.delete(filter, authentication);
	}

	@Transactional
	public List loadData() {
		return dao.loadData();
	}

}
