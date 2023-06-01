package com.savethepets.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="BOOKMARKS")
public class Bookmark {
	@EmbeddedId
	BookmarkId bookmarkId;
	Date timestamp;
}

@Embeddable
class BookmarkId implements Serializable {
    String userId;
    Long postId;
}