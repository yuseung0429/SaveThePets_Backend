package com.savethepets.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="USERS")
public class User {
	@Id
	String userId;
	String nickname;
	byte[] picture;
	String endpoint;
	String p256dh;
	String auth;
}
