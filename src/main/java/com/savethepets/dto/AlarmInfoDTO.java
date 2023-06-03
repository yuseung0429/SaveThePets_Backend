package com.savethepets.dto;

import java.time.LocalDateTime;

public class AlarmInfoDTO {
	Long alarmId;
    Long postId;
    String nickname;
    byte[] picture;
    int species;
    int breed;
    LocalDateTime timestamp;
    int type;
}
