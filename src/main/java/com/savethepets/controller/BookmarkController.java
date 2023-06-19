package com.savethepets.controller;

import com.savethepets.entity.Bookmark;
import com.savethepets.id.BookmarkId;
import com.savethepets.service.AuthServiceImpl;
import com.savethepets.service.BookmarkServiceImpl;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Description<br>
 *  - BookmarkController Class : Bookmark 관련 정보를 처리하는 BookmarkController Class<br>
 * <br>
 * Field<br>
 * 	- bookmarkService : 북마크 관련 처리를 위한 BookmarkService <br>
 *  - authService : 인증 관련 처리를 위한 AuthService <br>
 * Method<br>
 *  - createBookmark : 북마크를 생성하는 Service와 연결하는 메소드 <br> 
 *  - removeBookmark : 북마크를 삭제하는 Service와 연결하는 메소드 <br> 
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@RestController
@RequestMapping(value = "/bookmark")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BookmarkController {
	private final BookmarkServiceImpl bookmarkService;
	private final AuthServiceImpl authService;
	/**
	 * Description<br>
	 *  - createBookmark : 북마크를 생성하는 Service와 연결하는 메소드 <br> 
	 * <br>
	 * EndPoint<br>
	 *  - /bookmark<br>
	 * <br>
	 * Mapping method<br>
	 *  - PostMapping<br>
	 * @param token JWT
	 * @param json key에 "postId"가 포함된 json Object
	 * @return ResponseEntity와 true/false(생성 성공 여부)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@PostMapping()
	ResponseEntity<Boolean> createBookmark(@RequestHeader("token") String token, @RequestBody Map<String, Long> json) {
		String userId;
		Long temp = json.get("postId");
		if(temp!=null&&(userId = authService.validateToken(token))!=null)
			if(bookmarkService.createBookmark(new Bookmark(new BookmarkId(userId, temp),LocalDateTime.now()))==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};
	/**
	 * Description<br>
	 *  - removeBookmark : 북마크를 삭제하는 Service와 연결하는 메소드 <br> 
	 * <br>
	 * EndPoint<br>
	 *  - /bookmark<br>
	 * <br>
	 * Mapping method<br>
	 *  - DeleteMapping<br>
	 * @param token JWT
	 * @param json key에 "postId"가 포함된 json Object
	 * @return ResponseEntity와 true/false(삭제 성공 여부)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@DeleteMapping()
	ResponseEntity<Boolean> removeBookmark(@RequestHeader("token") String token, @RequestBody Map<String, Long> json){
		String userId;
		Long temp = json.get("postId");
		if((userId = authService.validateToken(token)) != null)
			if(bookmarkService.removeBookmark(new BookmarkId(userId, temp))==true)
				return new ResponseEntity<>(true, HttpStatus.OK);
			else
				return new ResponseEntity<>(false, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	};
}