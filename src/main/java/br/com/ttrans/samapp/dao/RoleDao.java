package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.Role;


public interface RoleDao {
	void add(Role role);
	void edit(Role role);
	void delete(Role role);
	Role find(int id);
	Role findByDesc(String text);
	List<Role> getAllRoles();
}
