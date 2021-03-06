package com.hlws.rest.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hlws.dto.LoginDTO;
import com.hlws.dto.TokenDTO;
import com.hlws.security.service.ITokenService;

@RestController
@RequestMapping("auth")
public class AuthenticationResource {

	private final Logger LOG = LoggerFactory.getLogger(AuthenticationResource.class);
	private final ITokenService tokenService;
	
	/*@Autowired
	SecurityContextHolder securityContext;*/

	@Autowired
	public AuthenticationResource(ITokenService tokenService) {
		this.tokenService = tokenService;
	}
	
	@PostMapping
	public ResponseEntity<?> authenticate(@RequestBody LoginDTO dto){
		final TokenDTO response = tokenService.getToken(dto.getUsername(), dto.getPassword());
		if(response != null) {
			LOG.info("User={} authenticated and logged into the system", dto.getUsername());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			LOG.error("User={} authentication failed", dto.getUsername());
			return new ResponseEntity<>("Authentication Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
