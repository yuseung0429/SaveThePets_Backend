package com.savethepets.dto;

import java.time.LocalDateTime;

import com.savethepets.entity.Alarm;
import com.savethepets.entity.Post;
import com.savethepets.entity.PostPicture;
import com.savethepets.entity.User;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AlarmInfoDTO {
	Long alarmId;
    Long postId;
    String nickname;
    String picture;
    int species;
    int breed;
    LocalDateTime timestamp;
    int type;
    
    public AlarmInfoDTO(Alarm alarm, User user) {
    	this.alarmId = alarm.getAlarmId();
    	this.postId = alarm.getPostId();
    	this.timestamp = alarm.getTimestamp();
    	this.type = alarm.getType();
    	this.nickname = user.getNickname();
    	this.picture = user.getPicture();
    }
    
    public AlarmInfoDTO(Alarm alarm, Post post, PostPicture postpicture) {
    	this.alarmId = alarm.getAlarmId();
    	this.postId = alarm.getPostId();
    	this.timestamp = alarm.getTimestamp();
    	this.type = alarm.getType();
    	this.species = post.getSpecies();
    	this.breed = post.getBreed();
    	this.picture = postpicture.getPicture();  	
    }   
}
