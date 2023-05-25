package com.savethepets.controller;

import com.savethepets.service.AlarmServiceImpl;

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
@RequestMapping(value = "/alarm")

public class AlarmController {
	private final AlarmServiceImpl alarmService;
	
	@PostMapping("/create")
	ResponseEntity<Boolean> createAlarm(@RequestBody AlarmInfoDTO alarmInfoDTO) {return new ResponseEntity<>(null, HttpStatus.OK);};
	
	@DeleteMapping("/remove")
	ResponseEntity<Boolean> removeAlarm(@RequestBody Long alarmId) {return new ResponseEntity<>(null, HttpStatus.OK);};
}
