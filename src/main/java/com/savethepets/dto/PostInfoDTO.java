package com.savethepets.dto;

import java.time.LocalDateTime;

import com.savethepets.entity.Post;
import com.savethepets.entity.PostPicture;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostInfoDTO {
    Long postId;
    LocalDateTime time;
    LocalDateTime timestamp;
    Double postLat;
    Double postLot;
    int type;
    int species;
    int breed;
    String picture;
    String address;
    
    public PostInfoDTO (Post post, PostPicture postpicture)
    {
    	this.postId = post.getPostId();
    	this.time = post.getTime();
    	this.timestamp = post.getTimestamp();
    	this.postLat = post.getLat();
    	this.postLot = post.getLot();
    	this.type = post.getType();
    	this.species = post.getSpecies();
    	this.breed = post.getBreed();
        this.address = post.getAddress();
    	this.picture = postpicture.getPicture();	
    }
}
