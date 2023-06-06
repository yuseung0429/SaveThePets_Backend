package com.savethepets.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UpdatePostDTO {
    Long postId;
    List<byte[]> pictures;
    String content;
    int species;
    int breed;
    int type;
    LocalDateTime time;
    Double postLat;
    Double postLot;
}
