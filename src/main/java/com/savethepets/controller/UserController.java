package com.savethepets.controller;

import com.savethepets.service.UserServiceImpl;
import com.savethepets.utility.Utilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethepets.dto.*;

@Slf4j
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
	private final UserServiceImpl userService;
	
	@GetMapping("/signup")
	ResponseEntity<TokenInfoDTO> signup(@RequestBody String kakaoToken) {
		TokenInfoDTO temp;
		// DB에 recode 삽입이 성공한 경우
		if((temp = userService.signup(kakaoToken)) != null)
			return new ResponseEntity<>(temp, HttpStatus.OK);
		// DB에 recode 삽입이 실패한 경우 (Id에 해당하는 record가 이미 있는 경우)
		else
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
	};
	
	@GetMapping("/leaveid")
	ResponseEntity<Boolean> leaveId(@RequestHeader("token") String token) {
		String userId;
		if((userId= Utilities.verifiy(token))!=null)
			// DB에서 recode를 삭제 성공한 경우
			if(userService.leaveId(userId)==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			// DB에서 recode를 삭제 실패한 경우
			else
				return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	
	@PutMapping("/update-nickname")
	ResponseEntity<Boolean> updateNickname(@RequestHeader("token") String token, @RequestBody String nickname) {
		String userId;
		if((userId= Utilities.verifiy(token))!=null)
			// DB에서 recode를 갱신 성공한 경우
			if(userService.updateNickname(userId, nickname)==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			// DB에서 recode를 갱신 실패한 경우
			else
				return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	
	@PutMapping("/update-picture")
	ResponseEntity<Boolean> updatePicture(@RequestHeader("token") String token, @RequestBody byte[] picture) {
		String userId;
		if((userId= Utilities.verifiy(token))!=null)
			// DB에서 recode를 갱신 성공한 경우
			if(userService.updatePicture(userId, picture)==true)
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
		if((userId = Utilities.verifiy(token)) != null)
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
		if((userId = Utilities.verifiy(token)) != null)
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
		if((userId = Utilities.verifiy(token)) != null)
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
		if((userId = Utilities.verifiy(token)) != null)
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
		if((userId = Utilities.verifiy(token)) != null)
			// DB에서 recode를 읽기 성공한 경우
			if((temp = userService.getBookmarks(userId))!=null)
				return new ResponseEntity<>(temp, HttpStatus.OK);
			// DB에서 recode를 읽기 실패한 경우
			else
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	
}
