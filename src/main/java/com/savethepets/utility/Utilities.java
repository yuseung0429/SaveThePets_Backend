package com.savethepets.utility;

import com.savethepets.dto.UserInfoDTO;
import com.savethepets.service.UserService;

public class Utilities {
	public static String verifiy(String token)
	{
		// not null && not expired && user exist in DB
		if(token != null)
			//token이 아닌 UserId 반환 => 테스트에서는 token을 반환 하는걸로 
			return token;
		else
			return null;
	}
	
	public static void testGetUserInfo(UserService userService, String userId)
	{
		UserInfoDTO temp = userService.getUserInfo(userId);
		System.out.println("------------------------------");
		System.out.println("■ 유저 정보 검색, userId = " + userId);
		if(temp != null)
		{
			System.out.println("\t결과 : 성공");
			System.out.println("\t - 아이디 : " + temp.getUserId());
			System.out.println("\t - 닉네임 : " + temp.getNickname());
			System.out.println("\t - 프로필사진 : " + temp.getPicture());
		}
		else
			System.out.println("\t결과 : 실패(없는 사용자)");
		System.out.println("------------------------------");
	}
	
	public static void testUpdateNickname(UserService userService, String userId, String changeNickname)
	{
		UserInfoDTO temp = userService.getUserInfo(userId);
		
		System.out.println("------------------------------");
		System.out.println("■ 유저 닉네임 변경, userId = " + userId);
		if(temp != null)
		{
			System.out.println("\t유저 존재 여부 : 성공");
			System.out.println("\t - 변경전 닉네임 : " + temp.getNickname());
			if(userService.updateNickname(userId, changeNickname)==true)
			{
				System.out.println("\t결과 : 성공");
				temp = userService.getUserInfo(userId);
				System.out.println("\t - 변경후 닉네임 : " + temp.getNickname());
			}
			else
				System.out.println("\t결과 : 실패(중복된 닉네임)");
		}
		else
			System.out.println("\t결과 : 실패(없는 사용자)");
		System.out.println("------------------------------");
	}
	
	public static void testUpdatePicture(UserService userService, String userId, byte[] changePicture)
	{
		UserInfoDTO temp = userService.getUserInfo(userId);
		
		System.out.println("------------------------------");
		System.out.println("■ 유저 프로필사진 변경, userId = " + userId);
		if(temp != null)
		{
			System.out.println("\t유저 존재 여부 : 성공");
			System.out.println("\t - 변경전 프로필사진 : " + temp.getPicture());
			if(userService.updatePicture(userId, changePicture)==true)
			{
				System.out.println("\t결과 : 성공");
				temp = userService.getUserInfo(userId);
				System.out.println("\t - 변경후 프로필사진 : " + temp.getPicture());
			}
		}
		else
			System.out.println("\t결과 : 실패(없는 사용자)");
		System.out.println("------------------------------");
	}
}
