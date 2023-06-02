package com.savethepets.controller;

import com.savethepets.service.BookmarkServiceImpl;
import com.savethepets.service.CommentServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethepets.dto.*;

@Slf4j
@RestController
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
public class CommentController {
	private final CommentServiceImpl commentService;
	
	@PostMapping()
	ResponseEntity<Boolean> createComment(@RequestBody CommentDTO commentDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@PutMapping()
	ResponseEntity<Boolean> updateComment(@RequestBody CommentDTO commentDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@DeleteMapping()
	ResponseEntity<Boolean> removeComment(@RequestBody Long commentId) {return new ResponseEntity<>(null, HttpStatus.OK);};
}
