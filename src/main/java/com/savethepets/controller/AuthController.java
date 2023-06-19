package com.savethepets.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.savethepets.dto.TokenInfoDTO;
import com.savethepets.service.AuthService;


import lombok.RequiredArgsConstructor;
/**
 * Description<br>
 *  - AuthController Class : 인증 관련 정보를 처리하는 AuthController Class<br>
 * <br>
 * Field<br>
 *  - authService : 인증 관련 처리를 위한 AuthService <br>
 * Method<br>
 *  - kakaoLogin : 카카오 소셜로그인하는 Service와 연결하는 메소드 <br> 
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@RestController
@RequestMapping(value = "/oauth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	private final AuthService authservice;
	/**
	 * Description<br>
	 *  - removeAlarm : 알람을 삭제하는 Service와 연결하는 메소드 <br> 
	 * <br>
	 * EndPoint<br>
	 *  - /oauth/kakao<br>
	 * <br>
	 * Mapping method<br>
	 *  - GetMapping<br>
	 * @param token JWT
	 * @param code key가 "code"인 RequestParameter
	 * @return ResponseEntity와 TokenInfoDTO({@link com.savethepets.dto.TokenInfoDTO})
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@GetMapping("/kakao")
	ResponseEntity<TokenInfoDTO> kakaoLogin(@RequestParam("code") String code) {
		TokenInfoDTO tokenInfoDTO = authservice.kakaoLogin(code);
		if(tokenInfoDTO != null)
			return new ResponseEntity<>(tokenInfoDTO, HttpStatus.OK);	
		else
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
}
