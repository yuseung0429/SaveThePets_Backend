package com.savethepets.dto;

import lombok.Getter;
import lombok.Setter;
/**
 * Description<br>
 *  - ReportDTO Class : Report 정보를 Frontend에서 전달받아 등록할때 사용하는 Data Transfer Object<br>
 * <br>
 * Field<br>
 *  - objectId : 게시물 Id 또는 댓글 Id<br>
 *  - reporterId : 신고한 사용자 Id<br>
 *  - reportType : 신고사유<br>
 *  - reportReason : 신고내용<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Getter
@Setter
public class ReportDTO {
    Long objectId;
    Boolean type;
    int reportType;
    String reportReason;
    
}
