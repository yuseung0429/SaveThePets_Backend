package com.savethepets.dto;

import java.util.Date;

public class PostInfoDTO {
    Long postId;
    Date time;
    Double postLat;
    Double postLot;
    int type;
    int species;
    int breed;
    Byte[] picture;
}
