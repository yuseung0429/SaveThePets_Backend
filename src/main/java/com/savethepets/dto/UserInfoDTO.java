package com.savethepets.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {
	String userId;
    String nickname;
    byte[] picture;
}
