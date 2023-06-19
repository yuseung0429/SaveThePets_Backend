package com.savethepets.dto;

import lombok.Getter;
import lombok.Setter;
/**
 * Description<br>
 *  - PushDTO Class : Push 정보를 Frontend에서 전달받아 등록할때 사용하는 Data Transfer Object<br>
 * <br>
 * Field<br>
 * 	- endpoint : Push알림을 받을 수신자의 브라우저나 장치 URL <br>
 *  - p256dh : Push알림 수신자와 Push 서비스 사이 사용되는 Public Key <br>
 *  - auth : Push 서비스에서 제공된 Authentication Token <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Getter
@Setter
public class PushDTO {
	String endpoint;
	String p256dh;
	String auth;
}
