package com.savethepets.service;

import com.savethepets.dto.TokenInfoDTO;

public interface AuthService {
	TokenInfoDTO kakaoLogin(String code);
	String generateToken(String userId);
	String validateToken(String token);

}
