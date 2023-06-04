package com.savethepets.entity;

import com.savethepets.id.PostPictureId;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="POSTPICTURES")
public class PostPicture {
	@EmbeddedId
	PostPictureId postpictureid;

	byte[] picture;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", insertable = false, updatable = false)
	private Post post;

	public PostPicture() {
	}

	public PostPicture(PostPictureId postPictureId, byte[] bytes) {
		this.postpictureid = postPictureId;
		this.picture = bytes;
	}


}

