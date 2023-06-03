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
    Double postLat;
    Double postLot;
    int type;
    int species;
    int breed;
    byte[] picture;
    
    public PostInfoDTO (Post post, PostPicture postpicture)
    {
    	this.postId = post.getPostId();
    	this.time = post.getTime();
    	this.postLat = post.getLat();
    	this.postLot = post.getLot();
    	this.type = post.getType();
    	this.species = post.getSpecies();
    	this.breed = post.getBreed();
    	this.picture = postpicture.getPicture();	
    }
}
