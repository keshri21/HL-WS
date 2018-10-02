package com.hlws.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hlws.dto.LoginDTO;
import com.hlws.dto.TokenDTO;
import com.hlws.response.APIResponse;
import com.hlws.security.service.ITokenService;

@RestController
@RequestMapping("auth")
public class AuthenticationResource {

	
	private final ITokenService tokenService;

	@Autowired
	public AuthenticationResource(ITokenService tokenService) {
		this.tokenService = tokenService;
	}
	
	@PostMapping
	public ResponseEntity<?> authenticate(@RequestBody LoginDTO dto){
		final String token = tokenService.getToken(dto.getUsername(), dto.getPassword());
		if(token != null) {
			final TokenDTO response = new TokenDTO();
			response.setToken(token);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Authentication Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
