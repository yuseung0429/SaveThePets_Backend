package com.savethepets.entity;

import com.savethepets.id.TimelineId;

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