package com.savethepets.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Description<br>
 *  - Alarm Class : Alarm Entity 클래스<br>
 * <br>
 * Field<br>
 *  - Long :: alarmId : 알림 Id <br>
 *  - String :: senderId : 발신자 사용자 Id <br>
 *  - String :: receiverId : 수신자 사용자 Id<br>
 * 	- Long :: postId : 게시물 Id <br>
 *  - Int :: type : 알림 타입(0:목격/1:실종/2:보호/3:분양/4:댓글) <br>
 *  - LocalDateTime :: timestamp : 알림 등록시간 <br>
 * <br>
 * Const Field<br>
 *  - Int :: MISSING : 실종 게시글<br>
 *  - Int :: SIGHTING : 목격 게시글<br>
 *  - Int :: PROTECTION : 보호 게시글<br>
 *  - Int :: ADOPTION : 분양 게시글<br>
 *  - Int :: COMMENT : 댓글<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ALARMS")
public class Alarm {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long alarmId;
	String senderId;
	String receiverId;
	Long postId;
	LocalDateTime timestamp;
	int type;
	
	public static final int MISSING = 0;
    public static final int SIGHTING = 1;
    public static final int PROTECTION = 2;
    public static final int ADOPTION = 3;
    public static final int COMMENT = 4;

	public Alarm(String senderId, String receiverId, Long postId, LocalDateTime now, int type) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.postId = postId;
		this.type = type;
		this.timestamp = now;
	}
	
	public Alarm(String receiverId, Long postId, LocalDateTime now, int type) {
		this.receiverId = receiverId;
		this.postId = postId;
		this.timestamp = now;
		this.type = type;
	}
	
	
}
