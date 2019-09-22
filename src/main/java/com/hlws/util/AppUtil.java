package com.hlws.util;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hlws.enums.Authority;
import com.hlws.model.User;

public class AppUtil {
	
	private static final DecimalFormat DECIMAL_3_FORMAT = new DecimalFormat("0.###");
	
	public static User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			return ((User)authentication.getDetails());
		}else {
			System.out.println("This should not happen");
			return new User();
		}
	}
	
	public static Double formatDecimalValue(Double value) {
		if(value == null) {
			return null;
		}else {
			return Double.parseDouble(DECIMAL_3_FORMAT.format(value));
		}
	}
	
	public static void setAuthenticationForBackgroundTasks(String companyId) {
		//set authentcation on securitycontext as this is schduled task and authentication will not be available
		// where company id is used
		List<Authority> roles = Arrays.asList(Authority.ROLE_ADMIN);
		User user = new User();
		user.setAuthorities(roles);
		user.setCompanyId(companyId);
		AuthenticationUtil.configureAuthentication(user);
	}
	
	public static void clearAuthenticationForBackgroundTasks() {
		AuthenticationUtil.clearAuthentication();
	}

}
