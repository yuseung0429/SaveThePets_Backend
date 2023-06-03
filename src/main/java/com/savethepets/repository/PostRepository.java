package com.savethepets.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.savethepets.entity.Post;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostRepository {
	private final EntityManager em;
	
	public void save(Post post) {
		em.persist(post);
	}
	
	public void remove(Post post) {
		em.remove(post);
	}
	
	public Post findOne(Long postId) {
        return em.find(Post.class, postId);
    }
	
	public List<Post> findAll(){
		String query = "select p from Posts p";
		return em.createQuery(query, Post.class).getResultList();
	}
	
	public List<Post> findByPostIds(List<Long> postIds){
		String query = "select p from Posts p where p.postId in :postIds";
		return em.createQuery(query, Post.class).setParameter("postIds", postIds).getResultList();
	}
	
	public List<Post> findByUserId(String userId){
		String query = "select p from Posts p where userId = :userId";
		return em.createQuery(query, Post.class).setParameter("userId", userId).getResultList();
	}
}
