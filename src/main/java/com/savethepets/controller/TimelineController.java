
package com.savethepets.controller;

import com.savethepets.service.TimelineServiceImpl;

import lombok.RequiredArgsConstructor;
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
@RequestMapping(value = "/timeline")
@RequiredArgsConstructor

public class TimelineController {
	//private final TimelineServiceImpl timelineService;
	
	@PostMapping()
	ResponseEntity<Boolean> createTimeline(@RequestBody TimelineDTO timelineDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@DeleteMapping()
	ResponseEntity<Boolean> removeTimeline(@RequestBody TimelineDTO timelineDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
}