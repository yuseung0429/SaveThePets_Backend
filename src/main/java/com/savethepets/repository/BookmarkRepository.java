package com.savethepets.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.savethepets.entity.Bookmark;
import com.savethepets.id.BookmarkId;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookmarkRepository {
	private final EntityManager em;
	
	public void save(Bookmark bookmark) {
		em.persist(bookmark);
	}
	
	public void remove(Bookmark bookmark) {
		em.remove(bookmark);
	}

	public void removeByPostId(Long postId){
		String query = "delete from Bookmark b where b.bookmarkId.postId = :postId";
		em.createQuery(query)
				.setParameter("postId", postId)
				.executeUpdate();
	}
	
	public Bookmark findOne(BookmarkId bookmarkId) {
	    return em.find(Bookmark.class, bookmarkId);
	}
	
	public List<Bookmark> findByUserId(String userId)
	{
		String query = "select b from Bookmark b where b.bookmarkId.userId = :userId";
		return em.createQuery(query, Bookmark.class).setParameter("userId", userId).getResultList();
	}
	
	public List<Long> findPostIdsByUserId(String userId)
	{
		String query = "select b.postId from Bookmark b where b.bookmarkId.userId = :userId";
		return em.createQuery(query, Long.class).setParameter("userId", userId).getResultList();
	}
}
