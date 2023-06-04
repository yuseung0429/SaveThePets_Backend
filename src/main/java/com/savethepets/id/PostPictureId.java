package com.savethepets.id;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class PostPictureId implements Serializable {
	@Column(name = "post_id")
	Long postId;
	int sequence;

	public PostPictureId() {
		this.sequence = 0;
	}


	public PostPictureId(Long postId, int i) {
		this.postId = postId;
		this.sequence = i;
	}
}