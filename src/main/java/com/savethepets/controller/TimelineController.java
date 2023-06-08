
package com.savethepets.controller;

import com.savethepets.entity.Timeline;
import com.savethepets.id.TimelineId;
import com.savethepets.service.TimelineServiceImpl;

import com.savethepets.utility.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.savethepets.dto.*;

@Slf4j
@RestController
@RequestMapping(value = "/timeline")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TimelineController {
	private final TimelineServiceImpl timelineService;
	
	@PostMapping()
	ResponseEntity<Boolean> createTimeline(@RequestHeader("token") String token,@RequestBody TimelineDTO timelineDTO) {
		String userId;
		if((userId = Utilities.verifiy(token)) != null)
			// DB에 recode 삽입이 성공한 경우
			if(timelineService.createTimeline(new Timeline(new TimelineId(timelineDTO.getMissingPostId(),timelineDTO.getSightPostId())))==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
				// DB에 recode 삽입이 실패한 경우 (Id에 해당하는 record가 이미 있는 경우)
			else
				return new ResponseEntity<>(false, HttpStatus.CONFLICT);
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};


	@DeleteMapping()
	ResponseEntity<Boolean> removeTimeline(@RequestHeader("token") String token, @RequestBody TimelineDTO timelineDTO) {
		String userId;
		if((userId = Utilities.verifiy(token)) != null)
			// DB에 recode 삭제가 성공한 경우
			if(timelineService.removeTimeline(new TimelineId(timelineDTO.getMissingPostId(),timelineDTO.getSightPostId()))==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
				// DB에 recode 삭제가 실패한 경우 (Id에 해당하는 record가 없는 경우)
			else
				return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};

}