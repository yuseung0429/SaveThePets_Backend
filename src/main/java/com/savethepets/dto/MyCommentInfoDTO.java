package com.savethepets.dto;

import java.time.LocalDateTime;

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
}
