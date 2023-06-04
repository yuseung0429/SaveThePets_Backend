package com.savethepets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.savethepets.dto.UserInfoDTO;
import com.savethepets.service.AlarmServiceImpl;
import com.savethepets.service.BookmarkServiceImpl;
import com.savethepets.service.ReportServiceImpl;
import com.savethepets.service.UserServiceImpl;
import com.savethepets.utility.Utilities;

@SpringBootTest
class SaveThePetsBackendApplicationTests {
	private final UserServiceImpl userService;
	private final BookmarkServiceImpl bookmarkService;
	private final ReportServiceImpl reportService;
	private final AlarmServiceImpl alarmService;
	
	@Autowired
	public SaveThePetsBackendApplicationTests(UserServiceImpl userService, ReportServiceImpl reportService, BookmarkServiceImpl bookmarkService, AlarmServiceImpl alarmService) {
		this.userService = userService;
		this.bookmarkService = bookmarkService;
		this.reportService = reportService;
		this.alarmService = alarmService;
	}
	
	@Test
	void contextLoads() {
//		//유저 게시물 불러오기
//		System.out.println("유저 게시물 불러오기");
//		//유저 댓글 불러오기
//		System.out.println("유저 댓글 불러오기");
//		//유저 알람 불러오기
//		System.out.println("유저 알람 불러오기");
//		//유저 북마크 불러오기
//		System.out.println("유저 북마크 불러오기");
//		//신고 작성하기
//		System.out.println("신고 작성하기");
//		//북마크 생성하기
//		System.out.println("북마크 생성하기");
//		//북마크 삭제하기
//		System.out.println("북마크 삭제하기");
//		//알람 삭제하기
//		System.out.println("알람 삭제하기");
		
		Utilities.testGetUserInfo(userService, "1");
		Utilities.testGetUserInfo(userService, "50");
		
		Utilities.testUpdateNickname(userService, "1", "김철수");
		Utilities.testUpdateNickname(userService, "1", "김철수");
		
		Utilities.testUpdatePicture(userService, "1", "leeyuseung".getBytes());
	}
}