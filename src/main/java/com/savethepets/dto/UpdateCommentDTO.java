package com.savethepets.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateCommentDTO {
    Long commentId;
    String content;

}
