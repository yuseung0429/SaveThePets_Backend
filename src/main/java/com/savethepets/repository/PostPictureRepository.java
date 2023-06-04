package com.savethepets.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.savethepets.entity.PostPicture;
import com.savethepets.id.PostPictureId;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostPictureRepository {
	private final EntityManager em;
	
	public void save(PostPicture postPicture) {
		em.persist(postPicture);
	}
	
	public void remove(PostPicture postPicture) {
		em.remove(postPicture);
	}
	
	public PostPicture findOne(PostPictureId postPictureId) {
		return em.find(PostPicture.class, postPictureId);
	}
	
	public List<PostPicture> findByPostId(Long postId) {
		String query = "select p from PostPicture p where p.postpictureid.postId = :postId";
		return em.createQuery(query, PostPicture.class).setParameter("postId", postId).getResultList();
	}
	
	public List<PostPicture> findByPostIds(List<Long> postIds) {
		String query = "select p from PostPicture p where (p.postId in :postIds) and (p.sequence = 0)";
		return em.createQuery(query, PostPicture.class).setParameter("postIds", postIds).getResultList();
	}
}
