package com.savethepets.service;

import com.savethepets.entity.User;

public interface UserService {
	boolean signup(User user);
	boolean leaveId(String userId);
	
}
