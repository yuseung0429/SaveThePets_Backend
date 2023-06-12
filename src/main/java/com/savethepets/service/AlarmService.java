package com.savethepets.service;

import com.savethepets.entity.Alarm;

public interface AlarmService {
	boolean createAlarm(Alarm alarm);
    boolean removeAlarm(Long alarmId);
    Alarm makeAlarm(Long missingPostId, Long sightPostId);
}
