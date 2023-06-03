package com.savethepets.entity;

import com.savethepets.id.TimelineId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TIMELINES")
public class Timeline {
	@EmbeddedId
	TimelineId timelindId;
}