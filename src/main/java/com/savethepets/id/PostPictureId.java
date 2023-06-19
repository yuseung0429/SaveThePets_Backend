package com.savethepets.id;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostPictureId implements Serializable {
	@Column(name = "post_id")
	Long postId;
	int sequence;

}