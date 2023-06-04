package com.savethepets.dto;

import java.time.LocalDateTime;

import com.savethepets.entity.Comment;
import com.savethepets.entity.Post;
import com.savethepets.entity.PostPicture;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyCommentInfoDTO {
    Long postId;
    Double postLat;
    Double postLot;
    int species;
    int breed;
    int type;
    byte[] picture;
    String content;
    LocalDateTime time;
    LocalDateTime timestamp;
    
    public MyCommentInfoDTO(Comment comment, Post post, PostPicture postpicture) {
    	this.content = comment.getContent();
    	this.timestamp = comment.getTimestamp();
    	this.postId = post.getPostId();
    	this.postLat = post.getLat();
    	this.postLot = post.getLot();
    	this.species = post.getSpecies();
    	this.breed = post.getBreed();
    	this.type = post.getType();
    	this.time = post.getTime();
    	this.picture = postpicture.getPicture();
    }
}
