package com.savethepets.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.savethepets.entity.Alarm;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
/**
 * Description<br>
 *  - AlarmRepository Class : Alarm Table에 접근 Repository Class<br>
 * <br>
 * Field<br>
 *  - em : Entity 관리 Object<br>
 * <br>
 * Method<br>
 *  - save : 알람을 저장하는 메소드<br>
 *  - remove : 알람을 삭제하는 메소드<br>
 *  - findOne : 알람을 검색하는 메소드<br>
 *  - findByUserId : 사용자Id와 일치하는 알람을 검색하는 메소드<br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Repository
@RequiredArgsConstructor
public class AlarmRepository {
	private final EntityManager em;
	/**
	 * Description<br>
	 *  - save : 알람을 저장하는 메소드<br>
	 * @param alarm Alarm Object
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public void save(Alarm alarm) {
		em.persist(alarm);
	}
	/**
	 * Description<br>
	 *  - remove : 알람을 삭제하는 메소드<br> 
	 * @param alarm Alarm Object
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public void remove(Alarm alarm) {
		em.remove(alarm);
	}
	/**
	 * Description<br>
	 *  - findOne : 알람을 검색하는 메소드<br>
	 * @param alarmId 알람 Id
	 * @return Alarm Object 또는 null(존재하지 않을 경우)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public Alarm findOne(Long alarmId) {
	    return em.find(Alarm.class, alarmId);
	}
	/**
	 * Description<br>
	 *  - findByUserId : 사용자Id와 일치하는 알람을 검색하는 메소드<br>
	 * @param userId 사용자 Id
	 * @return Alarm Object List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	public List<Alarm> findByUserId(String userId)
	{
		String query = "select a from Alarm a where a.receiverId = :userId";
		return em.createQuery(query, Alarm.class).setParameter("userId", userId).getResultList();
	}
}
