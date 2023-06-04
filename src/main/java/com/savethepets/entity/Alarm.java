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
	
	public static final int MISSING = 0;
    public static final int SIGHTING = 1;
    public static final int PROTECTION = 2;
    public static final int ADOPTION = 3;
    public static final int COMMENT = 4;
}
