package com.savethepets.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentDTO {
    Long postId;
    String userId; // 게시글 작성자 Id
    String content;
}
