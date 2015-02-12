package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.RoleDao;
import br.com.ttrans.samapp.model.Role;
import br.com.ttrans.samapp.service.RoleService;

@Repository
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao dao;

	@Transactional
	public void add(Role role) {
		dao.add(role);
	}

	@Transactional
	public void edit(Role role) {
		dao.edit(role);
	}

	@Transactional
	public void delete(Role role) {
		dao.delete(role);
	}

	@Transactional
	public Role find(int id) {
		return dao.find(id);
	}

	@Transactional
	public Role findByDesc(String text) {
		return dao.findByDesc(text);
	}

	@Transactional
	public List<Role> getAllRoles() {
		return dao.getAllRoles();
	}

}
