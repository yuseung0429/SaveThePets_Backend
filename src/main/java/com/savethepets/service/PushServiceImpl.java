package com.savethepets.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savethepets.dto.PushInfoDTO;
import com.savethepets.entity.Alarm;
import com.savethepets.entity.Post;
import com.savethepets.entity.PostPicture;
import com.savethepets.entity.User;
import com.savethepets.repository.PostPictureRepository;
import com.savethepets.repository.PostRepository;
import com.savethepets.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import nl.martijndwars.webpush.Notification;

@Service
@Transactional
@RequiredArgsConstructor
public class PushServiceImpl implements PushService{
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final PostPictureRepository postPictureRepository;
	
	@Override
	public boolean createPush(Alarm alarm) {
		User temp = userRepository.findOne(alarm.getReceiverId());
		PushInfoDTO pushDTO;
		if(alarm.getType() != Alarm.COMMENT)
		{
			Post tempPost = postRepository.findOne(alarm.getPostId());
			PostPicture tempPostPicture = postPictureRepository.findOne(alarm.getPostId());
			pushDTO = new PushInfoDTO(alarm, tempPost, tempPostPicture);
		}
		else
			pushDTO = new PushInfoDTO(alarm, temp);
		ObjectMapper mapper = new ObjectMapper();
        String json;
       
		try {
			json = mapper.writeValueAsString(pushDTO);
			nl.martijndwars.webpush.PushService pushService = new nl.martijndwars.webpush.PushService();
			Notification notification = new Notification(temp.getEndpoint(), temp.getP256dh(), temp.getAuth(), json);
			pushService.send(notification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
