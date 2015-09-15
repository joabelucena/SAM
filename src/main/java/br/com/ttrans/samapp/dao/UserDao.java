package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.User;

public interface UserDao {
	void addUser(User user);
	void editUser(User user);
	void deleteUser(int userId);
	User findUser(int userId);
	User findUserByName(String username);
	List<User> getAllUsers();
}
