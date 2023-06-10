package com.savethepets.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushDTO {
	String endpoint;
	String p256dh;
	String auth;
}
