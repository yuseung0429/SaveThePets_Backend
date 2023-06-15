package com.savethepets.dto;

import com.savethepets.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoDTO {
	String userId;
    String nickname;
    String picture;
    
    public UserInfoDTO(User user)
    {
    	this.userId = user.getUserId();
    	this.nickname = user.getNickname();
    	this.picture = user.getPicture();
    }
}
