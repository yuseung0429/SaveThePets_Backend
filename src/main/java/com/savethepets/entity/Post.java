package com.savethepets.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
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
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PostPicture> pictures;
}
