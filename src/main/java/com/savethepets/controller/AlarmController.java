package com.savethepets.controller;

import com.savethepets.service.AlarmServiceImpl;
import com.savethepets.service.AuthServiceImpl;
import com.savethepets.utility.Utilities;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
	ResponseEntity<Boolean> removeAlarm(@RequestHeader("token") String token, @RequestBody Long alarmId) {
		if(authService.validateToken(token) != null)
		{
			// DB에 recode 삭제가 성공한 경우
			if(alarmService.removeAlarm(alarmId) == true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			// DB에 recode 삭제가 실패한 경우 (Id에 해당하는 record가 없는 경우)
			else
				return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};
}
