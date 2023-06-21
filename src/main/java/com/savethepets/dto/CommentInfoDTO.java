package com.savethepets.dto;

import com.savethepets.entity.Comment;
import com.savethepets.entity.User;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class CommentInfoDTO {
    Long commentId;
    String userId;
    String nickname;
    String content;
    String picture;
    LocalDateTime timestamp;

    public CommentInfoDTO(Comment comment,User user) {
    	this.userId = user.getUserId();
        this.commentId = comment.getCommentId();
        this.nickname = user.getNickname();
        this.content = comment.getContent();
        this.picture = user.getPicture();
        this.timestamp = comment.getTimestamp();
    }
}
