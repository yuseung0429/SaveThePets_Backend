package com.savethepets.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ALARMS")
public class Alarm {
	@Id 
	Long alarmId;
	String senderId;
	String receiverId;
	Long postId;
	Date timestamp;
	int type;
}
