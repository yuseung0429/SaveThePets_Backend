package com.savethepets.dto;

import java.util.Date;
import java.util.List;

public class PostDetailDTO {
    Long postId;
    String userId;
    String nickname;
    Byte[] picture;
    String species;
    String breed;
    String content;
    Date time;
    int type;
    List<Byte[]> pictures;
    List<CommentDTO> comments;
    List<TimelineDTO> timeline;

}
