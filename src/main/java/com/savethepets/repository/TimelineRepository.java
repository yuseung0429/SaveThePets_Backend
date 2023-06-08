package com.savethepets.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.savethepets.entity.Timeline;
import com.savethepets.id.TimelineId;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TimelineRepository {
	private final EntityManager em;
	
	public void save(Timeline timeline) {
		em.persist(timeline);
	}
	
	public void remove(Timeline timeline) {
		em.remove(timeline);
	}
	
	public Timeline findOne(TimelineId timelineId) {
		return em.find(Timeline.class, timelineId);
	}
	
	public List<Timeline> findByMissingPostId(Long missingPostId) {
		String query = "select t from Timeline t where timelineId.missingPostId = :missingPostId";
		return em.createQuery(query,Timeline.class).setParameter("missingPostId", missingPostId).getResultList();
	}
	
}
