package com.hlws.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hlws.model.User;

public class AppUtil {
	
	public static String getCompanyIdFromLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			return ((User)authentication.getDetails()).getCompanyId();
		}else {
			System.out.println("This should not happen");
			return null;
		}
	}

}
