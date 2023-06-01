package com.savethepets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.savethepets.entity.User;
import com.savethepets.service.UserServiceImpl;



@SpringBootTest
class SaveThePetsBackendApplicationTests {
	private final UserServiceImpl userService;

	@Autowired
	public SaveThePetsBackendApplicationTests(UserServiceImpl userService) {
	    this.userService = userService;
	}
	@Test
	void contextLoads() {
//        User temp = new User();
//        temp.setUserId("yuseung0429@naver.com");
//        temp.setNickname("이유승");
//        byte[] imageData = {
//        	    -1, -40, -1, -32, 0, 17, 8, 6, 6, 7, 6, 5, 8, 7, 7, 7,
//        	    9, 9, 8, 10, 12, 20, 13, 12, 11, 11, 12, 25, 18, 19, 15, 20,
//        	    29, 26, 31, 30, 29, 26, 28, 28, 32, 36, 46, 39, 32, 34, 44, 35,
//        	    28, 28, 40, 55, 41, 44, 48, 49, 52, 52, 52, 31, 39, 57, 61, 56 };
//        temp.setPicture(imageData);
//        temp.setEndpoint("a");
//        temp.setP256dh("a");
//        temp.setAuth("a");
//        System.out.println(userService.signup(temp));
//		  System.out.println(userService.leaveId("yuseung0429@naver.com"));
	}
}