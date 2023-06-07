/*
package com.savethepets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.savethepets.dto.UserInfoDTO;
import com.savethepets.service.AlarmServiceImpl;
import com.savethepets.service.AuthServiceImpl;
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
	private final AuthServiceImpl authService;
	
	@Autowired
	public SaveThePetsBackendApplicationTests(UserServiceImpl userService, ReportServiceImpl reportService, BookmarkServiceImpl bookmarkService, AlarmServiceImpl alarmService, AuthServiceImpl authService) {
		this.userService = userService;
		this.bookmarkService = bookmarkService;
		this.reportService = reportService;
		this.alarmService = alarmService;
		this.authService = authService;
	}
	
	@Test
	void contextLoads() {

		//북마크 생성하기
		System.out.println("북마크 생성하기");
		//북마크 삭제하기
		System.out.println("북마크 삭제하기");
		//알람 삭제하기
		System.out.println("알람 삭제하기");
		
		Utilities.testGetUserInfo(userService, "1");
		Utilities.testGetUserInfo(userService, "50");
		Utilities.testUpdateNickname(userService, "1", "김철수");
		Utilities.testUpdateNickname(userService, "1", "김철수");
		Utilities.testUpdatePicture(userService, "1", "leeyuseung".getBytes());
		Utilities.testGetMyPosts(userService, "3");
		Utilities.testGetMyComments(userService, "3");
		Utilities.testGetAlarms(userService, "1");
		Utilities.testGetBookmarks(userService, "3");
		Utilities.testCreateReport(reportService, (long) 30, "2", true, 0, "바보1");
		Utilities.testCreateBookmark(bookmarkService, "1", (long)30);
		Utilities.testRemoveBookmark(bookmarkService, "1", (long)30);
		Utilities.testRemoveAlarm(alarmService, (long) 30);
		
		String token = Utilities.testGenerateToken(authService, "yuseung0429@naver.com");
		
		Utilities.testvalidateToken(authService, token);
		

	}
}
*/