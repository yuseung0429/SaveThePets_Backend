package com.savethepets.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name="POSTS")
public class Post {
	@Id
	Long postId;
	String userId;
	String content;
	int species;
	int breed;
	int type;
	int speciesAi;
	int breedAi;
	Double accuracy;
	Double lat;
	Double lot;
	LocalDateTime timestamp;
	LocalDateTime time;
}
