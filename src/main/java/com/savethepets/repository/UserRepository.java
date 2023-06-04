package com.savethepets.repository;

import com.savethepets.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;


@Repository
@RequiredArgsConstructor
public class UserRepository{
	private final EntityManager em;

	public void save(User user) {
		em.persist(user);
	}
	
	public void remove(User user) {
		em.remove(user);
	}
	
	public User findOne(String userId) {
        return em.find(User.class, userId);
    }
	
	public List<User> findByNickname(String nickname)
	{
		String query = "select u from User u where u.nickname = :nickname";
		return em.createQuery(query, User.class).setParameter("nickname", nickname).getResultList();
	}
	
}
