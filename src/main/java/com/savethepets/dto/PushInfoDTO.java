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
public class PushInfoDTO {
	Long postId;
	String nickname;
	String picture;
	int species;
	int breed;
	LocalDateTime timestamp;
	int type;
    public PushInfoDTO(Alarm alarm, User user) {
    	this.postId = alarm.getPostId();
    	this.timestamp = alarm.getTimestamp();
    	this.type = alarm.getType();
    	this.nickname = user.getNickname();
    	this.picture = user.getPicture();
    }
    
    public PushInfoDTO(Alarm alarm, Post post, PostPicture postpicture) {
    	this.postId = alarm.getPostId();
    	this.timestamp = alarm.getTimestamp();
    	this.type = alarm.getType();
    	this.species = post.getSpecies();
    	this.breed = post.getBreed();
    	this.picture = postpicture.getPicture();  	
    }   
	
	
}
