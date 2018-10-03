package com.hlws.security.service;

import com.hlws.dto.TokenDTO;

public interface ITokenService {
    TokenDTO getToken(String username, String password);
}
