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
	@Column(length = 1000)
	String content;
	int species;
	int breed;
	int type;
	int speciesAi;
	int breedAi;
	Double accuracy;
	Double lat;
	Double lot;
	String address;
	LocalDateTime timestamp;
	LocalDateTime time;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> comments;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PostPicture> pictures;

	public Post(String userId, String content, int species, int breed, int type, Double lot, Double lat, String address, LocalDateTime time) {
		this.userId = userId;
		this.content=content;
		this.species=species;
		this.breed=breed;
		this.type=type;
		this.lat=lat;
		this.lot=lot;
		this.address=address;
		this.time=time;
		this.timestamp=LocalDateTime.now();
	}

}
