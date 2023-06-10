package com.savethepets.service;

import com.savethepets.entity.Alarm;

public interface PushService {

	boolean createPush(String userId, Alarm alarm);
}
