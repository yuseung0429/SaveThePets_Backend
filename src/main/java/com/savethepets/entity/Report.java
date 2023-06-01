package com.savethepets.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
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

@Embeddable
class ReportId implements Serializable {
	Long objectId;
	String reporterId;
	Boolean type;
}