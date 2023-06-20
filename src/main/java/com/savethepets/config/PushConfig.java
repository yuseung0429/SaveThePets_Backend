package com.savethepets.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;


/**
 * Description<br>
 *  - PushConfig Class : 웹 Push 관련 Configuration 클래스<br>
 * <br>
 * Field<br>
 *  - publicKey : Push 공개키  <br>
 *  - privateKey : Push 개인키 <br>
 * <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Getter
@Configuration
public class PushConfig {
    @Value("${push.public-key}")
    private String publicKey;

    @Value("${push.private-key}")
    private String privateKey;

}