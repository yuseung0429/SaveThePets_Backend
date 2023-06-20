package com.savethepets.controller;

import com.savethepets.service.AuthServiceImpl;
import com.savethepets.service.UserServiceImpl;
import com.savethepets.utility.Utilities;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.savethepets.dto.*;
import com.savethepets.enums.Species;

/**
 * Description<br>
 *  - UserController Class : User 관련 정보를 처리하는 UserController Class<br>
 * <br>
 * Field<br>
 * 	- userService : 사용자 관련 처리를 위한 UserService <br>
 *  - authService : 인증 관련 처리를 위한 AuthService <br>
 * Method<br>
 *  - leaveId : 사용자를 삭제하는 Service와 연결하는 메소드 <br> 
 *  - updateNickname : 사용자의 닉네임을 갱신하는 Service와 연결하는 메소드 <br>
 *  - updatePicture : 사용자의 프로필사진을 갱신하는 Service와 연결하는 메소드 <br>
 *  - registerPush : 사용자의 Push알림 정보를 등록하는 Service와 연결하는 메소드 <br>
 *  - getUserInfo : 사용자의 정보를 반환하는 Service와 연결하는 메소드 <br>
 *  - getMyPosts : 사용자가 작성한 게시물을 반환하는 Service와 연결하는 메소드 <br>
 *  - getMyComments : 사용자가 작성한 댓글을 반환하는 Service와 연결하는 메소드 <br>
 *  - getAlarms : 사용자가 수신한 알람을 반환하는 Service와 연결하는 메소드 <br>
 *  - getBookmarks : 사용자가 북마크한 게시물을 반환하는 Service와 연결하는 메소드 <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	private final UserServiceImpl userService;
	private final AuthServiceImpl authService;
	/**
	 * Description<br>
	 *  - leaveId : 사용자를 삭제하는 Service와 연결하는 메소드 <br> 
	 * <br>
	 * EndPoint<br>
	 *  - /user/leaveid<br>
	 * <br>
	 * Mapping method<br>
	 *  - GetMapping<br>
	 * @param token JWT
	 * @return ResponseEntity와 true/false(삭제 성공 여부)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@GetMapping("/leaveid")
	ResponseEntity<Boolean> leaveId(@RequestHeader("token") String token) {
		String userId;
		if((userId= authService.validateToken(token))!=null)
			if(userService.leaveId(userId)==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	/**
	 * Description<br>
	 *  - updateNickname : 사용자의 닉네임을 갱신하는 Service와 연결하는 메소드 <br>
	 * <br>
	 * EndPoint<br>
	 *  - /user/update-nickname<br>
	 * <br>
	 * Mapping method<br>
	 *  - PutMapping<br>
	 * @param token JWT
	 * @param json key에 "nickname"이 포함된 json Object
	 * @return ResponseEntity와 true/false(갱신 성공 여부)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@PutMapping("/update-nickname")
	ResponseEntity<Boolean> updateNickname(@RequestHeader("token") String token, @RequestBody() Map<String, String> json) {
		String userId;
		String temp = json.get("nickname");
		if(temp!=null&&(userId= authService.validateToken(token))!=null)
			if(userService.updateNickname(userId, temp)==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	/**
	 * Description<br>
	 *  - updatePicture : 사용자의 프로필사진을 갱신하는 Service와 연결하는 메소드 <br>
	 * <br>
	 * EndPoint<br>
	 *  - /user/update-picture<br>
	 * <br>
	 * Mapping method<br>
	 *  - PutMapping<br>
	 * @param token JWT
	 * @param picture key에 "picture"인 MultipartFile Object
	 * @return ResponseEntity와 true/false(갱신 성공 여부)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@PutMapping("/update-picture")
	ResponseEntity<Boolean> updatePicture(@RequestHeader("token") String token, @RequestParam("picture") MultipartFile picture) {
		String userId;
		
		if(picture!=null &&(userId= authService.validateToken(token))!=null)
		{
			File temp = Utilities.convertMultipartFileToFile(picture);
			if(userService.updatePicture(userId, temp)==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	/**
	 * Description<br>
	 *  - registerPush : 사용자의 Push알림 정보를 등록하는 Service와 연결하는 메소드 <br>
	 * <br>
	 * EndPoint<br>
	 *  - /user/push<br>
	 * <br>
	 * Mapping method<br>
	 *  - PostMapping<br>
	 * @param token JWT
	 * @param pushDTO ({@link com.savethepets.dto.PushDTO})
	 * @return ResponseEntity와 true/false(등록 성공 여부)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@PostMapping("/push")
	ResponseEntity<Boolean> registerPush(@RequestHeader("token") String token, @RequestBody() PushDTO pushDTO) {
		String userId;
		if((userId= authService.validateToken(token))!=null)
			if(userService.registerPush(userId, pushDTO)==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	/**
	 * Description<br>
	 *  - getUserInfo : 사용자의 정보를 반환하는 Service와 연결하는 메소드 <br>
	 * <br>
	 * EndPoint<br>
	 *  - /user/info<br>
	 * <br>
	 * Mapping method<br>
	 *  - GetMapping<br>
	 * @param token JWT
	 * @return ResponseEntity와 UserInfoDTO({@link com.savethepets.dto.UserInfoDTO})
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@GetMapping("/info")
	ResponseEntity<UserInfoDTO> getUserInfo(@RequestHeader("token") String token) {
		String userId;
		if((userId = authService.validateToken(token))!=null)
			return new ResponseEntity<>(userService.getUserInfo(userId), HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	/**
	 * Description<br>
	 *  - getMyPosts : 사용자가 작성한 게시물을 반환하는 Service와 연결하는 메소드 <br>
	 * <br>
	 * EndPoint<br>
	 *  - /user/post<br>
	 * <br>
	 * Mapping method<br>
	 *  - GetMapping<br>
	 * @param token JWT
	 * @return ResponseEntity와 PostInfoDTO({@link com.savethepets.dto.PostInfoDTO}) List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@GetMapping("/post")
	ResponseEntity<List<PostInfoDTO>> getMyPosts(@RequestHeader("token") String token) {
		String userId;
		if((userId = authService.validateToken(token))!=null)
			return new ResponseEntity<>(userService.getMyPosts(userId), HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	/**
	 * Description<br>
	 *  - getMyComments : 사용자가 작성한 댓글을 반환하는 Service와 연결하는 메소드 <br>
	 * <br>
	 * EndPoint<br>
	 *  - /user/comment<br>
	 * <br>
	 * Mapping method<br>
	 *  - GetMapping<br>
	 * @param token JWT
	 * @return ResponseEntity와 MyCommentInfoDTO({@link com.savethepets.dto.MyCommentInfoDTO}) List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@GetMapping("/comment")
	ResponseEntity<List<MyCommentInfoDTO>> getMyComments(@RequestHeader("token") String token){
		String userId;
		if((userId = authService.validateToken(token)) != null)
			return new ResponseEntity<>(userService.getMyComments(userId), HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	/**
	 * Description<br>
	 *  - getAlarms : 사용자가 수신한 알람을 반환하는 Service와 연결하는 메소드 <br>
	 * <br>
	 * EndPoint<br>
	 *  - /user/alarm<br>
	 * <br>
	 * Mapping method<br>
	 *  - GetMapping<br>
	 * @param token JWT
	 * @return ResponseEntity와 AlarmInfoDTO({@link com.savethepets.dto.AlarmInfoDTO}) List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@GetMapping("/alarm")
	ResponseEntity<List<AlarmInfoDTO>> getAlarms(@RequestHeader("token") String token){
		String userId;
		if((userId = authService.validateToken(token)) != null)
			return new ResponseEntity<>(userService.getAlarms(userId), HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	/**
	 * Description<br>
	 *  - getBookmarks : 사용자가 북마크한 게시물을 반환하는 Service와 연결하는 메소드 <br>
	 * <br>
	 * EndPoint<br>
	 *  - /user/bookmark<br>
	 * <br>
	 * Mapping method<br>
	 *  - GetMapping<br>
	 * @param token JWT
	 * @return ResponseEntity와 PostInfoDTO({@link com.savethepets.dto.PostInfoDTO}) List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@GetMapping("/bookmark")
	ResponseEntity<List<PostInfoDTO>> getBookmarks(@RequestHeader("token") String token){
		String userId;
		if((userId = authService.validateToken(token)) != null)
			return new ResponseEntity<>(userService.getBookmarks(userId), HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	};
	
	
}
