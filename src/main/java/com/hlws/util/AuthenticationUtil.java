package com.hlws.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hlws.model.User;
import com.hlws.model.UserAuthentication;

public class AuthenticationUtil {

	private AuthenticationUtil() {
		
	}
	
	public static void clearAuthentication() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
	public static void configureAuthentication(User user) {		
		Authentication authentication = new UserAuthentication(user);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
