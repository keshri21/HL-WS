package com.hlws.security.service;

public interface ITokenService {
    String getToken(String username, String password);
}
