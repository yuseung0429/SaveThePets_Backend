
package com.savethepets.controller;

import com.savethepets.service.TimelineServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savethepets.dto;

@Slf4j
@RestController
@RequestMapping(value = "/timeline")

public class TimelineController {
	private final TimelineServiceImpl timelineService;
	
	@PostMapping("/create")
	ResponseEntity<Boolean> createTimeline(@RequestBody TimelineInfoDTO timelineInfoDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@DeleteMapping("/remove")
	ResponseEntity<Boolean> removeTimeline(@RequestBody TimelineInfoDTO timelineInfoDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
}