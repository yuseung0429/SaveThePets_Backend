package com.savethepets.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savethepets.entity.Alarm;
import com.savethepets.repository.AlarmRepository;
import com.savethepets.repository.PostRepository;

import lombok.RequiredArgsConstructor;
/**
 * Description<br>
 *  - AlarmServiceImpl Class : AlarmService를 구현한 구현체 클래스<br>
 * <br>
 * Field<br>
 *  - postRepository : Post Table 접근 Repository <br>
 *  - alarmRepository : Alarm Table 접근 Repository <br>
 * <br>
 * Method<br>
 *  - createAlarm : 알람을 생성하는 메소드 <br> 
 *  - removeAlarm : 알람을 삭제하는 메소드 <br>
 *  - makeAlarm : 목격 알람 Object를 생성하는 메소드 <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{

	private final PostRepository postRepository;
	private final AlarmRepository alarmRepository;

	/**
	 * Description<br>
	 *  - createAlarm : 알람을 생성하는 메소드 <br> 
	 * @param alarm Alarm Object
	 * @return true/false (생성 성공 여부) 
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
    @Override
    public boolean createAlarm(Alarm alarm)
    {
//    	if(alarm.getReceiverId().equals(alarm.getSenderId()))
//    		return false;
		alarmRepository.save(alarm);
		return true;
    }
    /**
	 * Description<br>
	 *  - removeAlarm : 알람을 삭제하는 메소드 <br>
	 * @param alarmId alarmId
	 * @return true/false (삭제 성공 여부) 
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
    @Override
    public boolean removeAlarm(Long alarmId) {
    	Alarm temp;
    	// DB에 AlarmId에 해당하는 record가 있는 경우
    	if((temp = alarmRepository.findOne(alarmId))!=null)
    	{
    		alarmRepository.remove(temp);
    		return true;
    	}
    	// DB에 AlarmId에 해당하는 record가 없는 경우
    	else
    		return false;
    }
    /**
	 * Description<br>
	 *  - makeAlarm : 목격 알람 Object를 생성하는 메소드 <br>
	 * @param missingPostId 실종게시글Id
	 * @param sightPostId 목격게시글Id
	 * @return Alarm Object
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
    @Override
    public Alarm makeAlarm(Long missingPostId, Long sightPostId)
    {
    	String tempUserId = postRepository.findOne(missingPostId).getUserId();
    	return new Alarm(tempUserId, sightPostId, LocalDateTime.now(), Alarm.SIGHTING);
    }
	
}
