package com.savethepets.entity;

import com.savethepets.id.PostPictureId;

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

