package com.savethepets.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savethepets.entity.Bookmark;
import com.savethepets.id.BookmarkId;
import com.savethepets.repository.BookmarkRepository;

import lombok.RequiredArgsConstructor;

/**
 * Description<br>
 *  - BookmarkServiceImpl Class : BookmarkService를 구현한 구현체 클래스<br>
 * <br>
 * Field<br>
 *  - bookmarkRepository : Bookmark Table 접근 Repository <br>
 * <br>
 * Method<br>
 *  - createAlarm : 북마크를 생성하는 메소드 <br> 
 *  - removeAlarm : 북마크를 삭제하는 메소드 <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService{

	private final BookmarkRepository bookmarkRepository;
	
	/**
	 * Description<br>
	 *  - createBookmark : 북마크를 생성하는 메소드 <br> 
	 * @param bookmark Bookmark Object
	 * @return true/false (생성 성공 여부) 
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
    @Override
    public boolean createBookmark(Bookmark bookmark) {
    	if(bookmarkRepository.findOne(bookmark.getBookmarkId())==null)
    	{
    		bookmarkRepository.save(bookmark);
    		return true;
    	}
    	else
    		return false;
    }
    /**
	 * Description<br>
	 *  - removeBookmark : 알람을 삭제하는 메소드 <br>
	 * @param bookmarkId BookmarkId({@link com.savethepets.id.BookmarkId})
	 * @return true/false (삭제 성공 여부) 
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
    @Override
    public boolean removeBookmark(BookmarkId bookmarkId) {
    	Bookmark temp;
    	if((temp = bookmarkRepository.findOne(bookmarkId))!=null)
    	{
    		bookmarkRepository.remove(temp);
    		return true;
    	}
    	else
    		return false;
    }
}
