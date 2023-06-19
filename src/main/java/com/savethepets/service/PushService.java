package com.savethepets.service;

import com.savethepets.entity.Alarm;
/**
 * Description<br>
 *  - PushService Interface<br>
 * <br>
 * Method <br>
 *  - createPush : Push 알림을 생성하는 메소드 <br> 
 * @author Yuseung lee.
 * @since 2023.06.19
 */
public interface PushService {
	boolean createPush(Alarm alarm);
}
