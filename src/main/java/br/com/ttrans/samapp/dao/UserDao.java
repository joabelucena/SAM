package br.com.ttrans.samapp.dao;

import java.util.List;

import br.com.ttrans.samapp.model.Users;

public interface UserDao {
	void addUser(Users user);
	void editUser(Users user);
	void deleteUser(int userId);
	Users findUser(int userId);
	Users findUserByName(String username);
	List<Users> getAllUsers();
}
