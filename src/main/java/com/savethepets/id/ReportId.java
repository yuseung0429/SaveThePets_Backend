package com.savethepets.id;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class ReportId implements Serializable {
	Long objectId;
	String reporterId;
	Boolean type;
}