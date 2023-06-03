package com.savethepets.entity;

import java.time.LocalDateTime;

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
@Table(name="ALARMS")
public class Alarm {
	@Id 
	Long alarmId;
	String senderId;
	String receiverId;
	Long postId;
	LocalDateTime timestamp;
	int type;
}
