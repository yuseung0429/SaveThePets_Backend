package com.savethepets.controller;

import com.savethepets.service.BookmarkServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethepets.dto.*;

@Slf4j
@RestController
@RequestMapping(value = "/bookmark")

public class BookmarkController {
	//private final BookmarkServiceImpl bookmarkService;
	
	@PostMapping("/create")
	ResponseEntity<Boolean> createBookmark(@RequestBody BookmarkDTO bookmarkDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@DeleteMapping("/remove")
	ResponseEntity<Boolean> removeBookmark(@RequestBody BookmarkDTO bookmarkDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
}