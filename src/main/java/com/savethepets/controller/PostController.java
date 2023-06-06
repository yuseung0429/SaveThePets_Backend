package com.savethepets.controller;

import com.savethepets.entity.Post;
import com.savethepets.service.PostServiceImpl;

import com.savethepets.utility.Utilities;
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
	ResponseEntity<Long> createPost(@RequestHeader("token") String token, @RequestBody CreatePostDTO createPostDTO) {
		String userId;
		if((userId = Utilities.verifiy(token)) != null){
			List<byte[]> pictures = createPostDTO.getPictures();
			Post post = new Post(userId, createPostDTO.getContent(),createPostDTO.getSpecies(),createPostDTO.getBreed(),createPostDTO.getType(),createPostDTO.getLot(),createPostDTO.getLat(),createPostDTO.getTime());
			Long postId = postService.createPost(post,pictures);
			return new ResponseEntity<>(postId, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};

	@DeleteMapping()
	ResponseEntity<Boolean> removePost(@RequestHeader("token") String token, @RequestBody Long postId) {
		String userId;
		if((userId = Utilities.verifiy(token)) != null)
			// DB에 recode 삭제가 성공한 경우
			if(postService.removePost(postId)==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
				// DB에 recode 삭제가 실패한 경우 (Id에 해당하는 record가 없는 경우)
			else
				return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};
	@PutMapping()
	ResponseEntity<Boolean> updatePost(@RequestHeader("token") String token, @RequestBody UpdatePostDTO updatePostDTO) {
		String userId;
		if((userId = Utilities.verifiy(token)) != null)
		{
			// DB에 recode 수정 성공한 경우
			if(postService.updatePost(updatePostDTO)==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
				// DB에 recode 수정 실패한 경우 (postId에 해당하는 record가 없는 경우)
			else
				return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};

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
