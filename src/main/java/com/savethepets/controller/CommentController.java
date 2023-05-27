package com.savethepets.controller;

import com.savethepets.service.CommentServiceImpl;

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

public class CommentController {
	//private final CommentServiceImpl commentService;
	
	@PostMapping("/create")
	ResponseEntity<Boolean> createComment(@RequestBody CommentInfoDTO commentInfoDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@PutMapping("/update")
	ResponseEntity<Boolean> updateComment(@RequestBody CommentInfoDTO commentInfoDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@DeleteMapping("/remove")
	ResponseEntity<Boolean> removeComment(@RequestBody Long commentId) {return new ResponseEntity<>(null, HttpStatus.OK);};
}
