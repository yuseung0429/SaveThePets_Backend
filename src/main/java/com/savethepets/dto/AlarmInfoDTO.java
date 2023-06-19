package com.savethepets.dto;

import java.time.LocalDateTime;

import com.savethepets.entity.Alarm;
import com.savethepets.entity.Post;
import com.savethepets.entity.PostPicture;
import com.savethepets.entity.User;

import lombok.Getter;
import lombok.Setter;

/**
 * Description<br>
 *  - AlarmInfoDTO Class : Alarm 정보를 Frontend로 전달할때 사용하는 Data Transfer Object<br>
 * <br>
 * Field<br>
 * 	- alarmId : 알림 Id <br>
 *  - postId : 게시물 Id <br>
 *  - nickname : 댓글 작성 사용자 닉네임<br>
 *  - picture : 댓글 작성 사용자 프로필사진 URL 또는 게시물 썸네일 URL<br>
 *  - species : 게시물 동물 종<br>
 *  - breed : 게시물 동물 품종<br>
 *  - type : 알림 타입(0:목격/1:실종/2:보호/3:분양/4:댓글)<br>
 *  - timestamp : 알림 등록시간<br>
 * <br>
 * <b>Note</b><br>
 *  # 댓글 Alarm인 경우<br>
 *    - picture : 댓글 작성 사용자 프로필사진 URL<br>
 *    - species : 기본값 (0)<br>
 *    - breed : 기본값 (0)<br>
 *  # 게시물 Alarm인 경우<br>
 *    - picture : 게시물 썸네일 URL<br>
 *    - nickname : 기본값 (null)<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Getter
@Setter
public class AlarmInfoDTO {
	Long alarmId;
    Long postId;
    String nickname;
    String picture;
    int species;
    int breed;
    int type;
    LocalDateTime timestamp;
    
    public AlarmInfoDTO(Alarm alarm, User user) {
    	this.alarmId = alarm.getAlarmId();
    	this.postId = alarm.getPostId();
    	this.nickname = user.getNickname();
    	this.picture = user.getPicture();
    	this.type = alarm.getType();
    	this.timestamp = alarm.getTimestamp();
    }
    
    public AlarmInfoDTO(Alarm alarm, Post post, PostPicture postpicture) {
    	this.alarmId = alarm.getAlarmId();
    	this.postId = alarm.getPostId();
    	this.picture = postpicture.getPicture();
    	this.species = post.getSpecies();
    	this.breed = post.getBreed();
    	this.type = alarm.getType();
    	this.timestamp = alarm.getTimestamp();
    }   
}
