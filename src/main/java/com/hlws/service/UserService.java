package com.hlws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.hlws.dal.IUserDAL;
import com.hlws.dto.UserDTO;
import com.hlws.enums.Authority;
import com.hlws.model.User;
import com.hlws.util.AppUtil;

@Service
public class UserService {

    @Autowired
    private IUserDAL userRepository;
    
    public User findByUserName(String userName, String companyId){
        User user = userRepository.findByUserName(userName, companyId);
        List<Authority> authorities = new ArrayList<>();
        try {
        	authorities.add(Authority.valueOf(user.getRoleName()));
        }catch(IllegalArgumentException e) {
        	authorities.add(Authority.ANONYMOUS);
        }
        user.setAuthorities(authorities);
        user.setCompanyId(companyId);
        return user;
    }
    
    public User save(User user) {
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		user.setActive(true);
		user.setCompanyId(AppUtil.getLoggedInUser().getCompanyId());
		return userRepository.save(user);
	}
    
    public boolean update(User user) {    	
		return userRepository.updateUser(user);
	}
    
    public void deactivate(String username) {
    	
		userRepository.deactivate(username);
	}
    
    public List<User> getListOfUser(String filter) {
		switch (filter) {
		case "all": 
			return userRepository.getAll();
		default:
			return userRepository.findActiveUsers();
		}
	}
    
    public List<UserDTO> getByRole(String role){
    	return userRepository.getByRole(role);
    }
    
    public boolean changePassword(User user) {
    	user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
    	return userRepository.changePassword(user);
    }
    
}
