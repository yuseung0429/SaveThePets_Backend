package com.savethepets.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CreatePostDTO {
    String content;
    List<byte[]> picture;
    int species;
    int breed;
    LocalDateTime time;
    Double lat;
    Double lot;
    int type;
}
