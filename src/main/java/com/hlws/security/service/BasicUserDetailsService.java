package com.hlws.security.service;

import com.hlws.model.User;
import com.hlws.service.UserService;
import com.hlws.util.AppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BasicUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public BasicUserDetailsService(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	String[] usernameAndDomain = StringUtils.split(
    	          username, AppConstants.USERNAME_DELIMETER);
    	if (usernameAndDomain == null || usernameAndDomain.length != 2) {
            throw new UsernameNotFoundException("Username and company must be provided");
        }
    	System.out.println("User parts: " + usernameAndDomain[0] + " and " + usernameAndDomain[1]);
        final User user = userService.findByUserName(usernameAndDomain[0], usernameAndDomain[1]);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User with username:" + username + " not found");
        }
    }
}
