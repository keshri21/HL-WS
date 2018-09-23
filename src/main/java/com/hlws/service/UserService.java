package com.hlws.service;

import com.hlws.dal.IUserDAL;
import com.hlws.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserDAL userRepository;

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
