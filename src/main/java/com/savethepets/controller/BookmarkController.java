package com.savethepets.controller;

import com.savethepets.entity.Bookmark;
import com.savethepets.id.BookmarkId;
import com.savethepets.service.AuthServiceImpl;
import com.savethepets.service.BookmarkServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/bookmark")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BookmarkController {
	private final BookmarkServiceImpl bookmarkService;
	private final AuthServiceImpl authService;
	
	@PostMapping()
	ResponseEntity<Boolean> createBookmark(@RequestHeader("token") String token, @RequestBody Map<String, Long> json) {
		String userId;
		if((userId = authService.validateToken(token)) != null)
			if(bookmarkService.createBookmark(new Bookmark(new BookmarkId(userId, json.get("postId")),LocalDateTime.now()))==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};
	
	@DeleteMapping()
	ResponseEntity<Boolean> removeBookmark(@RequestHeader("token") String token, @RequestBody Map<String, Long> json){
		String userId;
		if((userId = authService.validateToken(token)) != null)
			if(bookmarkService.removeBookmark(new BookmarkId(userId, json.get("postId")))==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};
}