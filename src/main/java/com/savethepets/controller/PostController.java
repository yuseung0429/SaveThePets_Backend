package com.savethepets.controller;

import com.savethepets.entity.Alarm;
import com.savethepets.entity.Post;
import com.savethepets.service.AlarmServiceImpl;
import com.savethepets.service.AuthServiceImpl;
import com.savethepets.service.PostServiceImpl;
import com.savethepets.service.PushServiceImpl;
import com.savethepets.service.UserServiceImpl;
import com.savethepets.utility.Utilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.savethepets.dto.*;

@RestController
@RequestMapping(value = "/post")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
	private final UserServiceImpl userService;
	private final PostServiceImpl postService;
	private final AlarmServiceImpl alarmService;
	private final AuthServiceImpl authService;
	private final PushServiceImpl pushService;

	@PostMapping(consumes = "multipart/form-data")
	ResponseEntity<Long> createPost(@RequestHeader("token") String token, CreatePostDTO createPostDTO) {
		String userId;
		if((userId = authService.validateToken(token)) != null){
			List<MultipartFile> pictures = createPostDTO.getPictures();
			Post post = new Post(userId, createPostDTO.getContent(),createPostDTO.getSpecies(),createPostDTO.getBreed(),createPostDTO.getType(),createPostDTO.getLot(),createPostDTO.getLat(), createPostDTO.getAddress(), createPostDTO.getTime());
			List<File> temp = Utilities.convertMultipartFileListToFileList(pictures);
			System.out.println(temp.size());
			Long postId = postService.createPost(post,temp);
			postService.sendPostNotification(postId);
			return new ResponseEntity<>(postId, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};

	@DeleteMapping()
	ResponseEntity<Boolean> removePost(@RequestHeader("token") String token, @RequestBody() Map<String, Long> json) {
		if((authService.validateToken(token)) != null)
			// DB에 recode 삭제가 성공한 경우
			if(postService.removePost(json.get("postId"))==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
				// DB에 recode 삭제가 실패한 경우 (Id에 해당하는 record가 없는 경우)
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};
	@PutMapping(consumes = "multipart/form-data")
	ResponseEntity<Boolean> updatePost(@RequestHeader("token") String token, UpdatePostDTO updatePostDTO) {
		if((authService.validateToken(token)) != null)
		{
			// DB에 recode 수정 성공한 경우
			if(postService.updatePost(updatePostDTO)==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
				// DB에 recode 수정 실패한 경우 (postId에 해당하는 record가 없는 경우)
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};

	@GetMapping("/list")
	ResponseEntity<List<PostInfoDTO>> getBoardPosts() {
		List<PostInfoDTO> posts = postService.getBoardPosts();
		return new ResponseEntity<>(posts, HttpStatus.OK);
	};
	
	@GetMapping("/map")
	ResponseEntity<List<PostInfoDTO>> getMapPosts(@ModelAttribute DistancePostDTO distancePostDTO) {
		List<PostInfoDTO> posts = postService.getMapPosts(distancePostDTO);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	};

	@GetMapping("/filtered")
	ResponseEntity<List<PostInfoDTO>> getFilteredPosts(@ModelAttribute FilterDTO filterDTO) {
		List<PostInfoDTO> posts = postService.getFilteredPosts(filterDTO);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	};

	@GetMapping("/{postId}")
	ResponseEntity<PostDetailedInfoDTO> getPostDetail(@PathVariable("postId") Long postId) {
		PostDetailedInfoDTO post = postService.getPostDetail(postId);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}


	@GetMapping("/mylost")
	ResponseEntity<List<PostInfoDTO>> getMyLostPosts(@RequestHeader("token") String token) {
		String userId;
		List<PostInfoDTO> posts;
		if((userId = authService.validateToken(token)) != null)
			// DB에서 recode를 읽기 성공한 경우
			if((posts = postService.getMyLostPosts(userId))!=null)
				return new ResponseEntity<>(posts, HttpStatus.OK);
				// DB에서 recode를 읽기 실패한 경우
			else
				return new ResponseEntity<>(null, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	
	@PostMapping("/analyze")
	void sendAnalyzeResult(@RequestBody() Map<String, Long> json) {
		Long missingPostId = json.get("missingPostId");
		Long sightPostId = json.get("sightPostId");
		Alarm temp = alarmService.makeAlarm(missingPostId, sightPostId);
		alarmService.createAlarm(temp);
		//pushService.createPush(temp);
	};
}
