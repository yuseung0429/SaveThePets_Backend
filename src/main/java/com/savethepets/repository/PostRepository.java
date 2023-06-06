package com.savethepets.repository;

import java.util.List;

import jakarta.persistence.TypedQuery;
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

	public Long create(Post post) {
		save(post);
		return post.getPostId();
	}
	
	public void remove(Post post) {
		em.remove(post);
	}
	
	public Post findOne(Long postId) {
        return em.find(Post.class, postId);
    }
	
	public List<Post> findAll(){
		String query = "select p from Post p";
		return em.createQuery(query, Post.class).getResultList();
	}
	
	public List<Post> findByPostIds(List<Long> postIds){
		String query = "select p from Post p where p.postId in :postIds";
		return em.createQuery(query, Post.class).setParameter("postIds", postIds).getResultList();
	}
	
	public List<Post> findByUserId(String userId){
		String query = "select p from Post p where userId = :userId";
		return em.createQuery(query, Post.class).setParameter("userId", userId).getResultList();
	}

	public List<Post> findAllPosts() {
		String query = "SELECT p FROM Post p";
		return em.createQuery(query, Post.class)
				.getResultList();
	}
	public List<Post> findLostPostsByUserId(String userId) {
		String query = "SELECT p FROM Post p WHERE p.userId = :userId AND p.type = 0 ";
		return em.createQuery(query, Post.class)
				.setParameter("userId", userId)
				.getResultList();
	}
}
