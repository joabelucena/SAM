package br.com.ttrans.samapp.service;

import java.util.List;

import br.com.ttrans.samapp.model.Menu;
import br.com.ttrans.samapp.model.Role;

public interface MenuService {
	public void add(Menu menu);
	public void edit(Menu menu);
	public void delete(Menu menu);
	public List<Menu> loadMenu(Role role);
}