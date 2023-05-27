package com.savethepets.dto;

import java.util.Date;

public class PostDTO {
    Long postId;
    Date time;
    Double lat;
    Double lot;
    int type;
    int species;
    int breed;
    Byte[] picture;
}
