package com.savethepets.id;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class PostPictureId implements Serializable {
	Long postId;
	int sequence;
}