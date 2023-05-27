package com.savethepets.dto;

import java.util.Date;
import java.util.List;

public class UpdatePostDTO {
    Long postId;
    List<Byte[]> picture;
    int species;
    int breed;
    Date time;
    Double lat;
    Double lot;

}
