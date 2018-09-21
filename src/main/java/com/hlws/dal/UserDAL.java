package com.hlws.dal;

import java.util.List;

import com.hlws.model.User;

public interface UserDAL {

	User save(User user);
	void updatePassword(String userName, String password);
	List<User> getAll();
	List<User> getActiveUsers();
	List<User> findByUserName(String userName);
	User findById(String id);
	void delete(User user);
}
