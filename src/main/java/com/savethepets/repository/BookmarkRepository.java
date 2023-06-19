package com.savethepets.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.savethepets.entity.Bookmark;
import com.savethepets.id.BookmarkId;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
/**
 * Description<br>
 *  - BookmarkRepository Class : Bookmark Table에 접근 Repository Class<br>
 * <br>
 * Field<br>
 *  - em : Entity 관리 Object<br>
 * <br>
 * Method<br>
 *  - save : 북마크를 저장하는 메소드<br>
 *  - remove : 북마크를 삭제하는 메소드<br>
 *  - findOne : 북마크를 검색하는 메소드<br>
 *  - findByUserId : 사용자Id와 일치하는 북마크를 검색하는 메소드<br>
 *  - findPostIdsByUserId : 사용자Id와 일치하는 게시물Id를 검색하는 메소드<br>
 *  - removeByPostId : 게시물Id와 일치하는 북마크를 삭제하는 메소드<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Repository
@RequiredArgsConstructor
public class BookmarkRepository {
	private final EntityManager em;
	/**
	 * Description<br>
	 *  - save : 북마크를 저장하는 메소드<br>
	 * @param bookmark Bookmark Object
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public void save(Bookmark bookmark) {
		em.persist(bookmark);
	}
	/**
	 * Description<br>
	 *  - remove : 북마크를 삭제하는 메소드<br>
	 * @param bookmark Bookmark Object
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public void remove(Bookmark bookmark) {
		em.remove(bookmark);
	}
	/**
	 * Description<br>
	 *  - findOne : 북마크를 검색하는 메소드<br>
	 * @param bookmarkId 북마크 Id
	 * @return Bookmark Object 또는 null(존재하지 않을 경우)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public Bookmark findOne(BookmarkId bookmarkId) {
	    return em.find(Bookmark.class, bookmarkId);
	}
	/**
	 * Description<br>
	 *  - findByUserId : 사용자Id와 일치하는 북마크를 검색하는 메소드<br>
	 * @param userId 사용자 Id
	 * @return Bookmark Object List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public List<Bookmark> findByUserId(String userId)
	{
		String query = "select b from Bookmark b where b.bookmarkId.userId = :userId";
		return em.createQuery(query, Bookmark.class).setParameter("userId", userId).getResultList();
	}
	/**
	 * Description<br>
	 *  - findPostIdsByUserId : 사용자Id와 일치하는 게시물Id를 검색하는 메소드<br>
	 * @param userId 사용자 Id
	 * @return PostId List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public List<Long> findPostIdsByUserId(String userId)
	{
		String query = "select b.postId from Bookmark b where b.bookmarkId.userId = :userId";
		return em.createQuery(query, Long.class).setParameter("userId", userId).getResultList();
	}
	/**
	 * Description<br>
	 *  - removeByPostId : 게시물Id와 일치하는 북마크를 삭제하는 메소드<br>
	 * @param postId 게시물 Id
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public void removeByPostId(Long postId){
		String query = "delete from Bookmark b where b.bookmarkId.postId = :postId";
		em.createQuery(query).setParameter("postId", postId).executeUpdate();
	}
}
