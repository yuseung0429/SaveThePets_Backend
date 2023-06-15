package com.savethepets.controller;

import com.savethepets.service.AlarmServiceImpl;
import com.savethepets.service.AuthServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(value = "/alarm")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AlarmController {
	private final AlarmServiceImpl alarmService;
	private final AuthServiceImpl authService;
	
	@DeleteMapping()
	ResponseEntity<Boolean> removeAlarm(@RequestHeader("token") String token, @RequestBody() Map<String, Long> json) {
		if(authService.validateToken(token) != null)
		{
			if(alarmService.removeAlarm(json.get("alarmId")) == true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};
}
