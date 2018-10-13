package com.hlws.dal;

import java.util.List;

import com.hlws.dto.UserDTO;
import com.hlws.model.User;

public interface IUserDAL extends IBaseDAL {

	User save(User user);
	void updatePassword(String userName, String password);
	List<User> getAll();
	List<User> findActiveUsers();
	User findByUserName(String userName, String companyId);
	User findById(String id);
	void deactivate(String username);
	List<UserDTO> getByRole(String role);
}
