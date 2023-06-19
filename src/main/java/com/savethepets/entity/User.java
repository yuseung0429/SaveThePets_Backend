package com.savethepets.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Description<br>
 *  - User Class : User Entity 클래스<br>
 * <br>
 * Field<br>
 *  - String :: userId : 사용자 Id <br>
 *  - String :: nickname : 사용자 닉네임 <br>
 *  - String :: picture : 사용자 프로필사진 URL<br>
 * 	- String :: endpoint : Push알림을 받을 수신자의 브라우저나 장치 URL <br>
 *  - String :: p256dh : Push알림 수신자와 Push 서비스 사이 사용되는 Public Key <br>
 *  - String :: auth : Push 서비스에서 제공된 Authentication Token <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="USERS")
public class User {
	@Id
	String userId;
	String nickname;
	String picture;
	String endpoint;
	String p256dh;
	String auth;
	
	public User(String userId, String nickname, String picture) {
		this.userId = userId;
		this.nickname = nickname;
		this.picture = picture;
	}
}
