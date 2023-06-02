package com.savethepets.id;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class TimelineId implements Serializable {
	Long missingPostId;
	Long sightingPostId;
}