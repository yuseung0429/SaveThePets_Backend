package com.savethepets.entity;

import com.savethepets.id.PostPictureId;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="POSTPICTURES")
public class PostPicture {
	@EmbeddedId
	PostPictureId postpictureid;

	String picture;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", insertable = false, updatable = false)
	private Post post;

	public PostPicture() {
	}

	public PostPicture(PostPictureId postPictureId, String picture) {
		this.postpictureid = postPictureId;
		this.picture = picture;
	}


}

