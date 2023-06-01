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
@Table(name="COMMENTS")
public class Comment {
	@Id
	Long commentId;
	Long postId;
	String userId;
	String content;
	Date timestamp;
}
