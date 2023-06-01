package com.savethepets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savethepets.entity.User;
import com.savethepets.repository.UserRepository;

import lombok.RequiredArgsConstructor;

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
}
