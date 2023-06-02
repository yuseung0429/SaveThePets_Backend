package com.savethepets.dto;

import java.util.Date;
import java.util.List;

public class CreatePostDTO {
    String content;
    List<byte[]> picture;
    int species;
    int breed;
    Date time;
    Double lat;
    Double lot;
    int type;
}
