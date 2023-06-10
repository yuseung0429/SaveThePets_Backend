package com.savethepets.dto;

import com.savethepets.entity.*;
import com.savethepets.id.BookmarkId;
import com.savethepets.id.PostPictureId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class PostDetailedInfoDTO {
    int species;
    int breed;
    String content;
    LocalDateTime time;
    int type;
    Boolean bookmarked;
    Double lat;
    Double lot;
    List<byte[]> pictures;
    List<CommentInfoDTO> comments;
    List<TimelineInfoDTO> timeline;

    public PostDetailedInfoDTO(Post post, List<PostPicture> postPictures, Boolean bookmarked, List<CommentInfoDTO> commentInfoDTOs, List<TimelineInfoDTO> timelineInfoDTOs) {
        this.species = post.getSpecies();
        this.breed = post.getBreed();
        this.content = post.getContent();
        this.time = post.getTime();
        this.type = post.getType();
        this.lat = post.getLat();
        this.lot = post.getLot();

        // 북마크 정보 설정
        this.bookmarked = bookmarked;
        // 게시물 사진 설정
        this.pictures = postPictures.stream()
                .map(PostPicture::getPicture)
                .collect(Collectors.toList());

        this.comments = commentInfoDTOs;

        // 게시물 타임라인 설정
        this.timeline = timelineInfoDTOs;

    }

}
