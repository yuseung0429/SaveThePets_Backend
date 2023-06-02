package com.savethepets.controller;

import com.savethepets.service.PostServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethepets.dto.*;

@Slf4j
@RestController
@RequestMapping(value = "/post")
@RequiredArgsConstructor
public class PostController {
	private final PostServiceImpl postService;
	
	@PostMapping()
	ResponseEntity<Boolean> createPost(@RequestBody CreatePostDTO createPostDTO) {return new ResponseEntity<>(HttpStatus.OK);};

	@DeleteMapping()
	ResponseEntity<Boolean> removePost(@RequestBody Long postId) {return new ResponseEntity<>(HttpStatus.OK);};

	@PutMapping()
	ResponseEntity<Boolean> updatePost(@RequestBody UpdatePostDTO updatePostDTO) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@GetMapping("/list/{number}")
	ResponseEntity<List<PostInfoDTO>> getBoardPosts(@PathVariable Long number) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@GetMapping("/map")
	ResponseEntity<List<PostInfoDTO>> getMapPosts(@ModelAttribute DistancePostDTO distancePostDTO) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@GetMapping("/filtered")
	ResponseEntity<List<PostInfoDTO>> getFilteredPosts(@ModelAttribute FilterDTO filterDTO) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@GetMapping("/{postId}")
	ResponseEntity<PostDetailedInfoDTO> getPostDetail(@PathVariable Long postId) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@GetMapping("/mylost")
	ResponseEntity<List<PostInfoDTO>> getMyLostPosts(@RequestHeader("token") String token) {return new ResponseEntity<>(HttpStatus.OK);};
	
	@PostMapping("/analyze")
	ResponseEntity<AnalyzedPictureDTO> analyzePictures(@RequestBody List<Byte[]> pictures) {return new ResponseEntity<>(HttpStatus.OK);};
}
