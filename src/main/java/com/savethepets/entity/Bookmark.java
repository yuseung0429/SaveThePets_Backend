package com.savethepets.entity;

import java.util.Date;

import com.savethepets.id.BookmarkId;

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