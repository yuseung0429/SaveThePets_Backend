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
    String userid; // 게시글 작성자 Id
    String nickname;
    byte[] profilePicture;
    int species;
    int breed;
    String content;
    LocalDateTime time;
    int type;
    Boolean bookmarked;
    Double lat;
    Double lot;
    String address;
    List<byte[]> pictures;
    List<CommentInfoDTO> comments;
    List<TimelineInfoDTO> timeline;

    public PostDetailedInfoDTO(Post post,User user,List<PostPicture> postPictures, Boolean bookmarked, List<CommentInfoDTO> commentInfoDTOs, List<TimelineInfoDTO> timelineInfoDTOs) {
        this.userid = user.getUserId();
        this.nickname = user.getNickname();
        this.profilePicture = user.getPicture();
        this.species = post.getSpecies();
        this.breed = post.getBreed();
        this.content = post.getContent();
        this.time = post.getTime();
        this.type = post.getType();
        this.lat = post.getLat();
        this.lot = post.getLot();
        this.address = post.getAddress();

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
