package com.savethepets.service;

import com.savethepets.entity.Bookmark;

public interface BookmarkService {
    boolean createBookmark(Bookmark bookmark);
    boolean removeBookmark(String userId, Long postId);

}
