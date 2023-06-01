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
@Table(name="TIMELINES")
public class Timeline {
	@EmbeddedId
	TimelineId timelindId;
}

@Embeddable
class TimelineId implements Serializable {
	Long missingPostId;
	Long sightingPostId;
	Boolean type;
}