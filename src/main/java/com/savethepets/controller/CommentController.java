package com.savethepets.controller;

import com.savethepets.entity.Alarm;
import com.savethepets.entity.Comment;
import com.savethepets.service.AlarmServiceImpl;
import com.savethepets.service.AuthServiceImpl;
import com.savethepets.service.CommentServiceImpl;
import com.savethepets.service.PushServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.savethepets.dto.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

	private final CommentServiceImpl commentService;
	private final AuthServiceImpl authService;
	private final AlarmServiceImpl alarmService;
	private final PushServiceImpl pushService;
	@PostMapping()
	ResponseEntity<Boolean> createComment(@RequestHeader("token") String token, @RequestBody CreateCommentDTO createCommentDTO) {
		String userId;
		if((userId = authService.validateToken(token)) != null)
			// DB에 recode 삽입이 성공한 경우
			if(commentService.createComment(new Comment(createCommentDTO.getPostId(),userId, createCommentDTO.getContent(), LocalDateTime.now()))==true){
				Alarm temp = new Alarm(userId,createCommentDTO.getUserId(),createCommentDTO.getPostId(),LocalDateTime.now(), 4);
				alarmService.createAlarm(temp);
				//pushService.createPush(temp);
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
				// DB에 recode 삽입이 실패한 경우
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};


	@PutMapping()
	ResponseEntity<Boolean> updateComment(@RequestHeader("token") String token, @RequestBody UpdateCommentDTO updateCommentDTO) {
		if((authService.validateToken(token)) != null)
		{
			// DB에 recode 수정 성공한 경우
			if(commentService.updateComment(updateCommentDTO)==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
				// DB에 recode 수정 실패한 경우 (postId에 해당하는 record가 없는 경우)
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};

	@DeleteMapping()
	ResponseEntity<Boolean> removeComment(@RequestHeader("token") String token, @RequestBody() Map<String, Long> json) {
		if((authService.validateToken(token)) != null)
			// DB에 recode 삭제가 성공한 경우
			if(commentService.removeComment(json.get("commentId")) == true)
				return new ResponseEntity<>(true, HttpStatus.OK);
				// DB에 recode 삭제가 실패한 경우 (Id에 해당하는 record가 없는 경우)
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};

}
