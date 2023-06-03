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
@Table(name="COMMENTS")
public class Comment {
	@Id
	Long commentId;
	Long postId;
	String userId;
	String content;
	LocalDateTime timestamp;
}
