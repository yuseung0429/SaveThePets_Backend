package com.savethepets.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="POSTPICTURES")
public class PostPicture {
	
	@EmbeddedId
	PostPictureId postpictureid;
	byte[] picture;
}

@Embeddable
class PostPictureId implements Serializable {
	Long postId;
	int sequence;
}