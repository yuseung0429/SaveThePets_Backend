package com.savethepets.controller;

import com.savethepets.service.PostServiceImpl;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethepets.dto.*;

@Slf4j
@RestController
@RequestMapping(value = "/post")
public class PostController {
	//private final PostServiceImpl postService;
	
	@PostMapping("/create")
	ResponseEntity<Boolean> createPost(@RequestBody CreatePostDTO createPostDTO) {return new ResponseEntity<>(HttpStatus.OK);};

	@DeleteMapping("/remove")
	ResponseEntity<Boolean> removePost(@RequestBody Long postId) {return new ResponseEntity<>(HttpStatus.OK);};

	@PutMapping("/update")
	ResponseEntity<Boolean> updatePost(@RequestBody UpdatePostDTO updatePostDTO) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@GetMapping("/list/{number}")
	ResponseEntity<List<PostDTO>> getBoardPosts(@PathVariable Long number) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@PostMapping("/map")
	ResponseEntity<List<PostDTO>> getMapPosts(@RequestBody DistancePostDTO distancePostDTO) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@PostMapping("/filtered")
	ResponseEntity<List<PostDTO>> getFilteredPosts(@RequestBody FilterDTO filterDTO) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@GetMapping("/{postId}")
	ResponseEntity<PostDetailDTO> getPostDetail(@PathVariable Long postId) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@PostMapping("/mylost")
	ResponseEntity<List<PostDTO>> getMyLostPosts(@RequestBody String userId) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@PostMapping("/analyze")
	ResponseEntity<AnalyzedPictureDTO> analyzePictures(@RequestBody List<Byte[]> pictures) {return new ResponseEntity<>(HttpStatus.OK);};
}
