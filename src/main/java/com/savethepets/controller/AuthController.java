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

@RestController
@RequestMapping(value = "/oauth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	private final AuthService authservice;
	@GetMapping("/kakao")
	ResponseEntity<TokenInfoDTO> kakaoLogin(@RequestParam("code") String code) {
		TokenInfoDTO tokenDTO = authservice.kakaoLogin(code);
		if(tokenDTO != null)
			return new ResponseEntity<>(tokenDTO, HttpStatus.OK);	
		else
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
}
