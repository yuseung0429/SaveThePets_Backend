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
/**
 * Description<br>
 *  - PushServiceImpl Class : PushService를 구현한 구현체 클래스<br>
 * <br>
 * Field<br>
 *  - userRepository : User Table 접근 Repository <br>
 *  - postRepository : Post Table 접근 Repository <br>
 *  - postPictureRepository : PostPicture Table 접근 Repository <br>
 * <br>
 * Method<br>
 *  - createPush : Push 알림을 생성하는 메소드 <br> 
 * @author Yuseung lee.
 * @since 2023.06.19
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PushServiceImpl implements PushService{
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final PostPictureRepository postPictureRepository;
	
	/**
	 * Description<br>
	 *  - createPush : Push 알림을 전송하는 메소드 <br>
	 * @param alarm Alarm Object
	 * @return true/false (전송 성공 여부) 
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
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
			return false;
		}
		return true;
	}
}
