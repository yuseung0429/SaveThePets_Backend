package com.savethepets.controller;

import com.savethepets.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethepets.dto.*;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {
	//private final UserServiceImpl userServiceImpl;
	
	@PostMapping("/signup")
	ResponseEntity<TokenDTO> signup(@RequestBody String kakaoToken) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@PostMapping("/leaveid")
	ResponseEntity<Boolean> leaveId(@RequestBody String userId) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@PutMapping("/update-nickname")
	ResponseEntity<Boolean> updateNickname(@RequestBody UpdateNicknameDTO updateNicknameDTO) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@PutMapping("/update-picture")
	ResponseEntity<Boolean> updatePicture(@RequestBody UpdatePictureDTO updatePictureDTO) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@PostMapping("/info")
	ResponseEntity<UserInfoDTO> getUserInfo(@RequestBody String userId) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@PostMapping("/post")
	ResponseEntity<List<PostDTO>> getMyPosts(@RequestBody String userId) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@PostMapping("/comment")
	ResponseEntity<List<MyCommentDTO>> getMyComments(@RequestBody String userId) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@PostMapping("/alarm")
	ResponseEntity<List<AlarmDTO>> getAlarms(@RequestBody String userId) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@PostMapping("/bookmark")
	ResponseEntity<List<PostDTO>> getBookmarks(@RequestBody String userId) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@PostMapping("/id")
	ResponseEntity<String> getUserId(@RequestBody String token) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
}
