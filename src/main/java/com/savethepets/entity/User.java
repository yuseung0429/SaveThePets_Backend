package com.savethepets.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
