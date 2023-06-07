package com.savethepets.entity;

import java.time.LocalDateTime;

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
@Table(name="COMMENTS")
public class Comment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long commentId;
	String userId;
	String content;
	LocalDateTime timestamp;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	Post post;

	public Comment(Long postId, String userId, String content, LocalDateTime now) {
		this.post = new Post();
		this.post.setPostId(postId);
		this.userId = userId;
		this.content = content;
		this.timestamp = now;
	}
}
