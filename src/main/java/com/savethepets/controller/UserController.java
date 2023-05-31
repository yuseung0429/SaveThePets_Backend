package com.savethepets.controller;

import com.savethepets.service.UserServiceImpl;

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
public class UserController {
	//private final UserServiceImpl userServiceImpl;
	
	@GetMapping("/signup")
	ResponseEntity<TokenInfoDTO> signup(@RequestBody String kakaoToken) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@GetMapping("/leaveid")
	ResponseEntity<Boolean> leaveId(@RequestHeader("token") String token) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@PutMapping("/update-nickname")
	ResponseEntity<Boolean> updateNickname(@RequestBody String nickname) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@PutMapping("/update-picture")
	ResponseEntity<Boolean> updatePicture(@RequestBody Byte[] picture) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@GetMapping("/info")
	ResponseEntity<UserInfoDTO> getUserInfo(@RequestHeader("token") String token) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@GetMapping("/post")
	ResponseEntity<List<PostInfoDTO>> getMyPosts(@RequestHeader("token") String token) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@GetMapping("/comment")
	ResponseEntity<List<MyCommentInfoDTO>> getMyComments(@RequestHeader("token") String token) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@GetMapping("/alarm")
	ResponseEntity<List<AlarmInfoDTO>> getAlarms(@RequestHeader("token") String token) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@GetMapping("/bookmark")
	ResponseEntity<List<PostInfoDTO>> getBookmarks(@RequestHeader("token") String token) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
}
