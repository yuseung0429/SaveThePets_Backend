package com.savethepets.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.savethepets.entity.Comment;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
	private final EntityManager em;
	
	public void save(Comment comment) {
		em.persist(comment);
	}
	public void remove(Comment comment) {
		em.remove(comment);
	}
	public Comment findOne(Long commentId) {
        return em.find(Comment.class, commentId);
    }
	public List<Comment> findByUserId(String userId)
	{
		String query = "select c from Comment c where c.userId = :userId";
		return em.createQuery(query, Comment.class).setParameter("userId", userId).getResultList();
	}
	public List<Comment> findByPostId(Long postId)
	{
		String query = "select c from Comment c where c.postId = :postId";
		return em.createQuery(query, Comment.class).setParameter("postId", postId).getResultList();
	}
}
