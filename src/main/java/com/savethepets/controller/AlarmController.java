package com.savethepets.controller;

import com.savethepets.service.AlarmServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(value = "/alarm")
@RequiredArgsConstructor
public class AlarmController {
	private final AlarmServiceImpl alarmService;
	
	@DeleteMapping()
	ResponseEntity<Boolean> removeAlarm(@RequestBody Long alarmId) {return new ResponseEntity<>(null, HttpStatus.OK);};
}
