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
public class TimelineId implements Serializable {
	Long missingPostId;
	Long sightingPostId;
}