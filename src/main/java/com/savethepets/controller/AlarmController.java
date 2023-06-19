package com.savethepets.controller;

import com.savethepets.service.AlarmServiceImpl;
import com.savethepets.service.AuthServiceImpl;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description<br>
 *  - AlarmController Class : Alarm 관련 정보를 처리하는 UserController Class<br>
 * <br>
 * Field<br>
 * 	- alarmService : 알람 관련 처리를 위한 AlarmService <br>
 *  - authService : 인증 관련 처리를 위한 AuthService <br>
 * Method<br>
 *  - removeAlarm : 알람을 삭제하는 Service와 연결하는 메소드 <br> 
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@RestController
@RequestMapping(value = "/alarm")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AlarmController {
	private final AlarmServiceImpl alarmService;
	private final AuthServiceImpl authService;
	/**
	 * Description<br>
	 *  - removeAlarm : 알람을 삭제하는 Service와 연결하는 메소드 <br> 
	 * <br>
	 * EndPoint<br>
	 *  - /alarm<br>
	 * <br>
	 * Mapping method<br>
	 *  - DeleteMapping<br>
	 * @param token JWT
	 * @param json key에 "alarmId"가 포함된 json Object
	 * @return ResponseEntity와 true/false(삭제 성공 여부)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@DeleteMapping()
	ResponseEntity<Boolean> removeAlarm(@RequestHeader("token") String token, @RequestBody() Map<String, Long> json) {
		Long temp = json.get("alarmId");
		if(temp!=null&&authService.validateToken(token)!=null)
		{
			if(alarmService.removeAlarm(temp) == true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};
}
