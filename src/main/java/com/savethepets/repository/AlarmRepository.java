package com.savethepets.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.savethepets.entity.Alarm;
import com.savethepets.entity.Post;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AlarmRepository {
	private final EntityManager em;
	
	public void save(Alarm alarm) {
		em.persist(alarm);
	}
	
	public void remove(Alarm alarm) {
		em.remove(alarm);
	}
	
	public Alarm findOne(Long alarmId) {
	    return em.find(Alarm.class, alarmId);
	}
	
	public List<Alarm> findByUserId(String userId)
	{
		String query = "select a from Alarm a where a.receiverId = :userId";
		return em.createQuery(query, Alarm.class).setParameter("userId", userId).getResultList();
	}
}
