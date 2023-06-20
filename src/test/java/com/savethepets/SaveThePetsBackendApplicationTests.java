//
//package com.savethepets;
//
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.NoSuchAlgorithmException;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.util.Base64;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.savethepets.service.AlarmServiceImpl;
//import com.savethepets.service.AuthServiceImpl;
//import com.savethepets.service.AwsServiceImpl;
//import com.savethepets.service.BookmarkServiceImpl;
//import com.savethepets.service.ReportServiceImpl;
//import com.savethepets.service.UserServiceImpl;
//
//@SpringBootTest
//class SaveThePetsBackendApplicationTests {
//	private final UserServiceImpl userService;
//	private final BookmarkServiceImpl bookmarkService;
//	private final ReportServiceImpl reportService;
//	private final AlarmServiceImpl alarmService;
//	private final AuthServiceImpl authService;
//	private final AwsServiceImpl awsService;
//	
//	
//	@Autowired
//	public SaveThePetsBackendApplicationTests(UserServiceImpl userService, ReportServiceImpl reportService, BookmarkServiceImpl bookmarkService, AlarmServiceImpl alarmService, AuthServiceImpl authService, AwsServiceImpl awsService) {
//		this.userService = userService;
//		this.bookmarkService = bookmarkService;
//		this.reportService = reportService;
//		this.alarmService = alarmService;
//		this.authService = authService;
//		this.awsService = awsService;
//	}
//	
//	@Test
//	void contextLoads() {		
//	}
//}