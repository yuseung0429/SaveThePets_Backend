package com.savethepets.dto;

import com.savethepets.entity.User;

import lombok.Getter;
import lombok.Setter;
/**
 * Description<br>
 *  - UserInfoDTO Class : User 정보를 Frontend로 전달할때 사용하는 Data Transfer Object<br>
 * <br>
 * Field<br>
 * 	- userId : 사용자 Id <br>
 *  - nickname : 사용자 닉네임 <br>
 *  - picture : 사용자 프로필사진 URL<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Getter
@Setter
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
