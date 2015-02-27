package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.MenuDao;
import br.com.ttrans.samapp.model.Menu;
import br.com.ttrans.samapp.model.Role;
import br.com.ttrans.samapp.service.MenuService;

@Repository
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao dao;
	
	@Transactional
	public void add(Menu menu) {
		dao.add(menu);
	}

	@Transactional
	public void edit(Menu menu) {
		dao.edit(menu);
	}

	@Transactional
	public void delete(Menu menu) {
		dao.delete(menu);
	}

	@Transactional
	public List<Menu> loadMenu(Role role) {
		return dao.loadMenu(role);
	}

}
