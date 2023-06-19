package com.savethepets.service;

import com.savethepets.entity.Alarm;
/**
 * Description<br>
 *  - AlarmService Interface<br>
 * <br>
 * Method <br>
 *  - createAlarm : 알람을 생성하는 메소드 <br> 
 *  - removeAlarm : 알람을 삭제하는 메소드 <br>
 *  - makeAlarm : 목격 알람 Object를 생성하는 메소드 <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
public interface AlarmService {
	boolean createAlarm(Alarm alarm);
    boolean removeAlarm(Long alarmId);
    Alarm makeAlarm(Long missingPostId, Long sightPostId);
}
