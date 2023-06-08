package com.savethepets.dto;

import com.savethepets.entity.Comment;
import com.savethepets.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CommentInfoDTO {
    Long commentId;
    String nickname;
    String content;
    byte[] picture;

    public CommentInfoDTO(Comment comment,User user) {
        this.commentId = comment.getCommentId();
        this.nickname = user.getNickname();
        this.content = comment.getContent();
        this.picture = user.getPicture();
    }
}
