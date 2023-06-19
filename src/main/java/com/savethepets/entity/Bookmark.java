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
/**
 * Description<br>
 *  - Bookmark Class : Bookmark Entity 클래스<br>
 * <br>
 * Field<br>
 *  - BookmarkId({@link com.savethepets.id.BookmarkId}) :: reportId : 신고 Id <br>
 *  - LocalDateTime :: timestamp : 북마크 등록시간<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
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