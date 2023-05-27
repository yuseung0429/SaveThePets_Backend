package com.savethepets.dto;

import java.util.Date;
import java.util.List;

public class PostDetailDTO {
    Long postId;
    String userId;
    String nickname;
    Byte[] picture;
    int species;
    int breed;
    String content;
    Date time;
    int type;
    Boolean bookmarked;
    List<Byte[]> pictures;
    List<CommentDTO> comments;
    List<TimelineDTO> timeline;

}
