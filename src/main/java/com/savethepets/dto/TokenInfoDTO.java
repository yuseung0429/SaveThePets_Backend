package com.savethepets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * Description<br>
 *  - TokenInfoDTO Class : JWT와 인증성공여부를 Frontend로 전달할때 사용하는 Data Transfer Object<br>
 * <br>
 * Field<br>
 * 	- token : JWT <br>
 *  - authenticated : 인증성공여부 <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@AllArgsConstructor
@Getter
@Setter
public class TokenInfoDTO {
	String token;
	Boolean authenticated;
}
