package com.savethepets.id;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class ReportId implements Serializable {
	Long objectId;
	String reporterId;
	Boolean type;
}