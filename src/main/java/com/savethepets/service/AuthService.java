package com.savethepets.service;

import com.savethepets.dto.TokenInfoDTO;
/**
 * Description<br>
 *  - AuthService Interface<br>
 * <br>
 * Method <br>
 *  - kakaoLogin : 인가코드로 JWT를 발행하는 메소드 <br>
 * 	- generateToken : 사용자Id로 JWT를 발행하는 메소드 <br>
 *  - validateToken : JWT를 검증하는 메소드 <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
public interface AuthService {
	TokenInfoDTO kakaoLogin(String code);
	String generateToken(String userId);
	String validateToken(String token);
}
