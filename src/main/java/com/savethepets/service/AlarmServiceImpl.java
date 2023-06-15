package com.savethepets.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savethepets.entity.Alarm;
import com.savethepets.repository.AlarmRepository;
import com.savethepets.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{

	private final PostRepository postRepository;
	private final AlarmRepository alarmRepository;

    @Override
    public boolean createAlarm(Alarm alarm)
    {
		alarmRepository.save(alarm);
		return true;
    }
    
    @Override
    public Alarm makeAlarm(Long missingPostId, Long sightPostId)
    {
    	String tempUserId = postRepository.findOne(missingPostId).getUserId();
    	return new Alarm(tempUserId, sightPostId, LocalDateTime.now(), Alarm.SIGHTING);
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
