package com.savethepets.service;

import com.savethepets.dto.AlarmInfoDTO;
import com.savethepets.dto.MyCommentInfoDTO;
import com.savethepets.dto.PostInfoDTO;
import com.savethepets.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savethepets.entity.User;
import com.savethepets.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	@Autowired
	private final UserRepository userRepository;

	@Override
	public boolean signup(User user) {
		User temp = userRepository.findOne(user.getUserId());
		if(temp != null)
			return false;
		else
		{
			userRepository.save(user);
			return true;
		}
	}
	
	@Override
	public boolean leaveId(String userId) {
		User temp = userRepository.findOne(userId);

		if(temp != null)
		{
			userRepository.remove(temp);
			return true;
		}
		else
			return false;
	}


	@Override
	public boolean updateNickname(String userId, String nickname) {
		return false;
	}

	@Override
	public boolean updatePicture(String userId, byte[] picture) {
		return false;
	}

	@Override
	public UserInfoDTO getUserInfo(String userId) {
		return null;
	}

	@Override
	public List<PostInfoDTO> getMyPosts(String userId) {
		return null;
	}

	@Override
	public List<MyCommentInfoDTO> getMyComments(String userId) {
		return null;
	}

	@Override
	public List<AlarmInfoDTO> getAlarms(String userId) {
		return null;
	}

	@Override
	public List<PostInfoDTO> getBookmarks(String userId) {
		return null;
	}


}
