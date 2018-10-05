package com.hlws.service;

import com.hlws.dal.IUserDAL;
import com.hlws.enums.Authority;
import com.hlws.model.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    
    public User save(User user, boolean createFlag) {
    	if(createFlag) {
    		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
    		user.setActive(true);
    	}
		return userRepository.save(user);
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
}
