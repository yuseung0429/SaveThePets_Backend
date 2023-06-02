package com.savethepets.entity;

import com.savethepets.id.ReportId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="REPORTS")
public class Report {
	@EmbeddedId
	ReportId reportId;
	int reportType;
	String reportReason;
}