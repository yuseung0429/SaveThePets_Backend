package com.savethepets.dto;

import com.savethepets.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {
	String userId;
    String nickname;
    byte[] picture;
    
    public UserInfoDTO(User user)
    {
    	this.userId = user.getUserId();
    	this.nickname = user.getNickname();
    	this.picture = user.getPicture();
    }
}
