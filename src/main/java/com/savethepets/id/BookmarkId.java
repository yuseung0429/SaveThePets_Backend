package com.savethepets.id;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Description<br>
 *  - BookmarkId Class : Bookmark Entity의 복합키 클래스<br>
 * <br>
 * Field<br>
 *  - userId : 북마크한 사용자 Id<br>
 *  - postId : 북마크한 게시물 Id<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@SuppressWarnings("serial")
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkId implements Serializable {
    String userId;
    Long postId;
}