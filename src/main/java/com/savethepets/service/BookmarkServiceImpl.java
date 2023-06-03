package com.savethepets.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savethepets.entity.Bookmark;
import com.savethepets.id.BookmarkId;
import com.savethepets.repository.BookmarkRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService{

	private final BookmarkRepository bookmarkRepository;
	
    @Override
    public boolean createBookmark(Bookmark bookmark) {
    	// DB에 BookmarkId에 해당하는 record가 없는 경우
    	if(bookmarkRepository.findOne(bookmark.getBookmarkId())==null)
    	{
    		bookmarkRepository.save(bookmark);
    		return true;
    	}
    	// DB에 BookmarkId에 해당하는 record가 있는 경우
    	else
    		return false;
    }
    @Override
    public boolean removeBookmark(BookmarkId bookmarkId) {
    	Bookmark temp;
    	// DB에 BookmarkId에 해당하는 record가 있는 경우
    	if((temp = bookmarkRepository.findOne(bookmarkId))!=null)
    	{
    		bookmarkRepository.remove(temp);
    		return true;
    	}
    	// DB에 BookmarkId에 해당하는 record가 없는 경우
    	else
    		return false;
    }
}
