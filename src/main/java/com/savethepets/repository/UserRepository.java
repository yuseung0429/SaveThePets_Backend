package com.savethepets.repository;

import com.savethepets.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

/**
 * Description<br>
 *  - UserRepository Class : User Table에 접근 Repository Class<br>
 * <br>
 * Field<br>
 *  - em : Entity 관리 Object<br>
 * <br>
 * Method<br>
 *  - save : 사용자를 저장하는 메소드<br>
 *  - remove : 사용자를 삭제하는 메소드<br>
 *  - findOne : 사용자를 검색하는 메소드<br>
 *  - findByNickname : 닉네임과 일치하는 사용자를 검색하는 메소드<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Repository
@RequiredArgsConstructor
public class UserRepository{
	private final EntityManager em;
	/**
	 * Description<br>
	 *  - save : 사용자를 저장하는 메소드 <br> 
	 * @param user User Object
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public void save(User user) {
		em.persist(user);
	}
	/**
	 * Description<br>
	 *  - remove : 사용자를 삭제하는 메소드 <br> 
	 * @param user User Object
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public void remove(User user) {
		em.remove(user);
	}
	/**
	 * Description<br>
	 *  - findOne : 사용자를 검색하는 메소드 <br> 
	 * @param userId 사용자 Id
	 * @return User Object 또는 null(존재하지 않을 경우)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public User findOne(String userId) {
        return em.find(User.class, userId);
    }
	/**
	 * Description<br>
	 *  - findByNickname : 닉네임과 일치하는 사용자를 검색하는 메소드 <br> 
	 * @param nickname 닉네임
	 * @return User Object List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public List<User> findByNickname(String nickname)
	{
		String query = "select u from User u where u.nickname = :nickname";
		return em.createQuery(query, User.class).setParameter("nickname", nickname).getResultList();
	}
	
}
