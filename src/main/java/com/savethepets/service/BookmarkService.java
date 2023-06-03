package com.savethepets.service;

import com.savethepets.entity.Bookmark;
import com.savethepets.id.BookmarkId;

public interface BookmarkService {
    boolean createBookmark(Bookmark bookmark);
    boolean removeBookmark(BookmarkId bookmarkId);
}
