package com.savethepets.controller;

import com.savethepets.service.BookmarkServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/bookmark")
@RequiredArgsConstructor
public class BookmarkController {
	private final BookmarkServiceImpl bookmarkService;
	
	@PostMapping()
	ResponseEntity<Boolean> createBookmark(@RequestBody Long postId) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@DeleteMapping()
	ResponseEntity<Boolean> removeBookmark(@RequestBody Long postId) {return new ResponseEntity<>(null, HttpStatus.OK);};
}