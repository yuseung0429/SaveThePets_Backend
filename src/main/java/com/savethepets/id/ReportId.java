package com.savethepets.id;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * Description<br>
 *  - ReportId Class : Report Entity의 복합키 클래스<br>
 * <br>
 * Field<br>
 *  - objectId : 게시물 Id 또는 댓글 Id<br>
 *  - reporterId : 신고한 사용자 Id<br>
 *  - type : 신고타입(0:게시물/1:댓글)<br>
 * <br>
 * <b>Note</b><br>
 *  # 게시물 신고의 경우<br>
 *    - objectId : 게시물 Id<br>
 *  # 댓글 신고의 경우<br>
 *    - objectId : 댓글 Id<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@SuppressWarnings("serial")
@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class ReportId implements Serializable {
	Long objectId;
	String reporterId;
	Boolean type;
}