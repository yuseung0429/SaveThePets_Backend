package com.savethepets.controller;

import com.savethepets.service.AuthServiceImpl;
import com.savethepets.service.UserServiceImpl;
import com.savethepets.utility.Utilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.savethepets.dto.*;

@Slf4j
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	private final UserServiceImpl userService;
	private final AuthServiceImpl authService;
	
	@GetMapping("/leaveid")
	ResponseEntity<Boolean> leaveId(@RequestHeader("token") String token) {
		String userId;
		
		if((userId= authService.validateToken(token))!=null)
			// DB에서 recode를 삭제 성공한 경우
			if(userService.leaveId(userId)==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			// DB에서 recode를 삭제 실패한 경우
			else
				return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	
	@PutMapping("/update-nickname")
	ResponseEntity<Boolean> updateNickname(@RequestHeader("token") String token, @RequestBody() Map<String, String> json) {
		String userId;
		if((userId= authService.validateToken(token))!=null)
			// DB에서 recode를 갱신 성공한 경우
			if(userService.updateNickname(userId, json.get("nickname"))==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			// DB에서 recode를 갱신 실패한 경우
			else
				return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	
	@PutMapping("/update-picture")
	ResponseEntity<Boolean> updatePicture(@RequestHeader("token") String token, @RequestBody() Map<String, String> json) {
		String userId;
		if((userId= authService.validateToken(token))!=null)
			// DB에서 recode를 갱신 성공한 경우
			if(userService.updatePicture(userId, json.get("picture").getBytes())==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			// DB에서 recode를 갱신 실패한 경우
			else
				return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	
	@GetMapping("/info")
	ResponseEntity<UserInfoDTO> getUserInfo(@RequestHeader("token") String token) {
		String userId;
		UserInfoDTO temp;
		if((userId = authService.validateToken(token)) != null)
			// DB에서 recode를 읽기 성공한 경우
			if((temp = userService.getUserInfo(userId))!=null)
				return new ResponseEntity<>(temp, HttpStatus.OK);
			// DB에서 recode를 읽기 실패한 경우
			else
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	
	@GetMapping("/post")
	ResponseEntity<List<PostInfoDTO>> getMyPosts(@RequestHeader("token") String token) {
		String userId;
		List<PostInfoDTO> temp;
		if((userId = authService.validateToken(token)) != null)
			// DB에서 recode를 읽기 성공한 경우
			if((temp = userService.getMyPosts(userId))!=null)
				return new ResponseEntity<>(temp, HttpStatus.OK);
			// DB에서 recode를 읽기 실패한 경우
			else
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	
	@GetMapping("/comment")
	ResponseEntity<List<MyCommentInfoDTO>> getMyComments(@RequestHeader("token") String token){
		String userId;
		List<MyCommentInfoDTO> temp;
		if((userId = authService.validateToken(token)) != null)
			// DB에서 recode를 읽기 성공한 경우
			if((temp = userService.getMyComments(userId))!=null)
				return new ResponseEntity<>(temp, HttpStatus.OK);
			// DB에서 recode를 읽기 실패한 경우
			else
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	
	@GetMapping("/alarm")
	ResponseEntity<List<AlarmInfoDTO>> getAlarms(@RequestHeader("token") String token){
		String userId;
		List<AlarmInfoDTO> temp;
		if((userId = authService.validateToken(token)) != null)
			// DB에서 recode를 읽기 성공한 경우
			if((temp = userService.getAlarms(userId))!=null)
				return new ResponseEntity<>(temp, HttpStatus.OK);
			// DB에서 recode를 읽기 실패한 경우
			else
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	
	@GetMapping("/bookmark")
	ResponseEntity<List<PostInfoDTO>> getBookmarks(@RequestHeader("token") String token){
		String userId;
		List<PostInfoDTO> temp;
		if((userId = authService.validateToken(token)) != null)
			// DB에서 recode를 읽기 성공한 경우
			if((temp = userService.getBookmarks(userId))!=null)
				return new ResponseEntity<>(temp, HttpStatus.OK);
			// DB에서 recode를 읽기 실패한 경우
			else
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	
}
