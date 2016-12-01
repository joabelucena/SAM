package br.com.ttrans.samapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import br.com.ttrans.samapp.model.User;

public interface UserService {
	public void add(User user, Authentication authentication);
	public void edit(User user, Authentication authentication);
	public void delete(User user, Authentication authentication);
	User findUserByName(String username);
	public List<User> loadData();
}
