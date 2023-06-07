package com.savethepets.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethepets.dto.TokenInfoDTO;
import com.savethepets.service.AuthService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/oauth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authservice;
	@GetMapping("/kakao")
	ResponseEntity<TokenInfoDTO> kakaoLogin(String code) {
		TokenInfoDTO tokenDTO = authservice.kakaoLogin(code);
		if(tokenDTO != null)
		{
			return new ResponseEntity<>(tokenDTO, HttpStatus.FOUND);
		}		
		else
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
}
