package com.hlws.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public static final String company_id = "companyid";
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken authRequest = getAuthRequest(request);
		setDetails(request, authRequest);
       
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	private UsernamePasswordAuthenticationToken getAuthRequest(
      HttpServletRequest request) {
  
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String domain = request.getParameter(company_id);
 
 
        String usernameDomain = String.format("%s%s%s", username.trim(), 
          String.valueOf(Character.LINE_SEPARATOR), domain);
        return new UsernamePasswordAuthenticationToken(usernameDomain, password);
    }

	
}
