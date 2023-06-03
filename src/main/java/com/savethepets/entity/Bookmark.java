package com.savethepets.entity;

import java.time.LocalDateTime;

import com.savethepets.id.BookmarkId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="BOOKMARKS")
public class Bookmark {
	@EmbeddedId
	BookmarkId bookmarkId;
	LocalDateTime timestamp;
}