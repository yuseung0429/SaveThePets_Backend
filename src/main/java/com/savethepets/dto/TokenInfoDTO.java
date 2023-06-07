package com.savethepets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TokenInfoDTO {
	String token;
	Boolean authenticated;
}
