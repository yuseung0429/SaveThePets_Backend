package com.savethepets.utility;

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
}
