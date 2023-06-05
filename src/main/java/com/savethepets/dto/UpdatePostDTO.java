package com.savethepets.dto;

import java.time.LocalDateTime;
import java.util.List;

public class UpdatePostDTO {
    Long postId;
    List<byte[]> picture;
    String content;
    int species;
    int breed;
    LocalDateTime time;
    Double postLat;
    Double postLot;
}
