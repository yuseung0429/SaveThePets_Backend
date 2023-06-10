package com.savethepets.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savethepets.entity.Alarm;
import com.savethepets.repository.AlarmRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{

	private final AlarmRepository alarmRepository;

    @Override
    public boolean createAlarm(Alarm alarm)
    {
		alarmRepository.save(alarm);
		return true;
    }
	
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
}
