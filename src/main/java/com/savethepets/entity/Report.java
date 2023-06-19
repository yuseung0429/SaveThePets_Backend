package com.savethepets.entity;

import com.savethepets.id.ReportId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Description<br>
 *  - Report Class : Report Entity 클래스<br>
 * <br>
 * Field<br>
 *  - ReportId({@link com.savethepets.id.ReportId}) :: reportId : 신고 Id <br>
 *  - Int :: reportType : 사용자 닉네임 <br>
 *  - String :: reportReason :: 사용자 프로필사진 URL<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="REPORTS")
public class Report {
	@EmbeddedId
	ReportId reportId;
	int reportType;
	String reportReason;
}