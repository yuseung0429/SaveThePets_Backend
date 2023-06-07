package com.savethepets.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentDTO {
    Long postId;
    String content;
}
