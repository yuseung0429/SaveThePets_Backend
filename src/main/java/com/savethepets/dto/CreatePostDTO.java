package com.savethepets.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


@Getter @Setter
public class CreatePostDTO {
    String content;
    List<MultipartFile> pictures;
    int species;
    int breed;
    LocalDateTime time;
    Double lat;
    Double lot;
    String address;
    int type;
}
