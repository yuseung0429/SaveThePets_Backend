package com.savethepets.dto;

import java.time.LocalDateTime;

import com.savethepets.entity.Post;
import com.savethepets.entity.PostPicture;

import lombok.Getter;
import lombok.Setter;

/**
 * Description<br>
 *  - PostInfoDTO Class : Post 정보를 Frontend로 전달할때 사용하는 Data Transfer Object<br>
 * <br>
 * Field<br>
 * 	- postId : 게시물 Id <br>
 *  - picture : 게시물 썸네일 URL<br>
 *  - species : 게시물 동물 종 index<br>
 *  - breed : 게시물 동물 품종 index<br>
 *  - type : 게시물 타입(0:목격/1:실종/2:보호/3:분양)<br>
 *  - postLat : 게시물 위도<br>
 *  - postLot : 게시물 경도<br>
 *  - address : 게시물 등록주소<br>
 *  - time : 사용자 등록시간<br>
 *  - timestamp : 게시물 등록시간 또는 북마크 등록시간<br>
 * <br>
 * <b>Note</b><br>
 *  # 사용자 자신이 작성한 게시물을 불러오는 경우<br>
 *    - timestamp : 게시물 등록시간<br>
 *  # 사용자가 북마크한 게시물을 불러오는 경우<br>
 *    - timestamp : 북마크 등록시간<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Getter
@Setter
public class PostInfoDTO {
    Long postId;
    String picture;
    int species;
    int breed;
    int type;
    Double postLat;
    Double postLot;
    String address;
    LocalDateTime time;
    LocalDateTime timestamp;

    public PostInfoDTO (Post post, PostPicture postpicture)
    {
    	this.postId = post.getPostId();
    	this.picture = postpicture.getPicture();
    	this.species = post.getSpecies();
    	this.breed = post.getBreed();
    	this.type = post.getType();
    	this.postLat = post.getLat();
    	this.postLot = post.getLot();
    	this.address = post.getAddress();
    	this.time = post.getTime();
    	this.timestamp = post.getTimestamp();	
    }
}
