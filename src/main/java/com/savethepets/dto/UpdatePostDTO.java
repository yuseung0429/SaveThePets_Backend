package com.savethepets.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdatePostDTO {
    Long postId;
    List<MultipartFile> pictures;
    String content;
    int species;
    int breed;
    int type;
    LocalDateTime time;
    Double postLat;
    Double postLot;
    String address;
}
