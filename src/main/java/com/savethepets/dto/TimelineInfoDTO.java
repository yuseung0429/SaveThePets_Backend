package com.savethepets.dto;

import java.time.LocalDateTime;

public class TimelineInfoDTO {
    Long sightingPostId;
    LocalDateTime time;
    Double postLat;
    Double postLot;
    int species;
    int breed;
    byte[] picture;
}
