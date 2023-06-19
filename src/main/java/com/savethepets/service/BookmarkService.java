package com.savethepets.service;

import com.savethepets.entity.Bookmark;
import com.savethepets.id.BookmarkId;
/**
 * Description<br>
 *  - BookmarkService Interface<br>
 * <br>
 * Method <br>
 *  - createBookmark : 북마크를 생성하는 메소드 <br> 
 *  - removeBookmark : 북마크를 삭제하는 메소드 <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
public interface BookmarkService {
    boolean createBookmark(Bookmark bookmark);
    boolean removeBookmark(BookmarkId bookmarkId);
}
