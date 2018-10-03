package com.hlws.security.service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hlws.dto.TokenDTO;
import com.hlws.enums.Authority;
import com.hlws.model.User;
import com.hlws.util.AppConstants;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JsonWebTokenService implements ITokenService {
    private static int tokenExpirationTime = 30;

    @Value("${security.token.secret.key}")
    private String tokenKey;

    private final UserDetailsService userDetailsService;

    @Autowired
    public JsonWebTokenService(final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public TokenDTO getToken(final String username, final String password) {
        if (username == null || password == null) {
            return null;
        }
        final User user = (User) userDetailsService.loadUserByUsername(username);
        TokenDTO tokenDTO;
        Map<String, Object> tokenData = new HashMap<>();
        if (password.equals(user.getPassword())) {
            tokenData.put("username", user.getUsername() + 
            		AppConstants.USERNAME_DELIMETER + user.getCompanyId());
            tokenData.put("role", CollectionUtils.isEmpty(user.getAuthorities()) ? 
            		Authority.ANONYMOUS.getAuthority() :user.getAuthorities().get(0).getAuthority());
            tokenData.put("token_create_date", LocalDateTime.now());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, tokenExpirationTime);
            tokenData.put("token_expiration_date", calendar.getTime());
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);
            tokenDTO = new TokenDTO();
            tokenDTO.setToken(jwtBuilder.signWith(SignatureAlgorithm.HS512, tokenKey).compact());
            tokenDTO.setRole(tokenData.get("role").toString());
            return tokenDTO;

        } else {
            throw new AuthenticationServiceException("Authentication error");
        }
    }

    public static void setTokenExpirationTime(final int tokenExpirationTime) {
        JsonWebTokenService.tokenExpirationTime = tokenExpirationTime;
    }
}
