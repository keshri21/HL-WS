package com.hlws.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hlws.model.User;

public class AppUtil {
	
	public static User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			return ((User)authentication.getDetails());
		}else {
			System.out.println("This should not happen");
			return new User();
		}
	}

}
