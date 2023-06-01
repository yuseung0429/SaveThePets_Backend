package com.savethepets.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
	Date timestamp;
	Date time;
}
