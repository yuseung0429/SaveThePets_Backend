package com.savethepets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.savethepets.entity.User;
import com.savethepets.repository.UserRepository;
import com.savethepets.service.UserServiceImpl;

import jakarta.persistence.EntityManager;



@SpringBootTest
class SaveThePetsBackendApplicationTests {
	private final UserServiceImpl userService;
	private final UserRepository userRepository;
	@Autowired
	public SaveThePetsBackendApplicationTests(UserServiceImpl userService, UserRepository userRepository) {
	    this.userService = userService;
	    this.userRepository = userRepository;
	}
	@Test
	void contextLoads() {
//		for(int i = 0; i<10000; i++)
//		{
//			User temp = new User();
//			temp.setUserId("user"+ i +"@naver.com");
//			temp.setNickname("유저"+i);
//			byte[] data = {(byte) i};
//			temp.setPicture(data);
//			temp.setEndpoint(String.valueOf(i));
//			temp.setP256dh(String.valueOf(i));
//			temp.setAuth(String.valueOf(i));
//			userService.signup(temp);
//		}
		User temp = userRepository.findOne("user100@naver.com");
		System.out.println(temp.getNickname());
	}
}