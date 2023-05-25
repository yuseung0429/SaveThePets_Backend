package com.savethepets.dto;

import java.util.Date;
import java.util.List;

public class UpdatePostDTD {
    Long postId;
    List<Byte[]> picture;
    String species;
    String breed;
    Date time;
    Double lat;
    Double lot;

}
