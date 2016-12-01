package br.com.ttrans.samapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ttrans.samapp.dao.UserDao;
import br.com.ttrans.samapp.model.User;
import br.com.ttrans.samapp.service.UserService;

@Repository
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dao;

	@Transactional
	public void add(User user, Authentication authentication) {
		dao.add(user, authentication);
	}

	@Transactional
	public void edit(User user, Authentication authentication) {
		dao.edit(user, authentication);
	}

	@Transactional
	public void delete(User user, Authentication authentication) {
		dao.delete(user, authentication);
	}

	@Transactional
	public User findUserByName(String username) {
		return dao.findUserByName(username);
	}

	@Transactional
	public List<User> loadData() {
		return dao.loadData();
	}

}
