package com.savethepets.dto;

import java.util.Date;
import java.util.List;

public class PostDetailedInfoDTO {
    int species;
    int breed;
    String content;
    Date time;
    int type;
    Boolean bookmarked;
    List<Byte[]> pictures;
    List<CommentInfoDTO> comments;
    List<TimelineInfoDTO> timeline;
}
