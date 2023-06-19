package com.savethepets.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
/**
 * Description<br>
 *  - JwtConfig Class : JWT 관련 Configuration 클래스<br>
 * <br>
 * Field<br>
 *  - key : JWT 대칭키 <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Configuration
@Getter
public class JwtConfig {
	@Value("${jwt.key}")
	private String key;
}