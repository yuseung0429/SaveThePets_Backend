package com.savethepets.service;

import com.savethepets.dto.AlarmInfoDTO;
import com.savethepets.dto.MyCommentInfoDTO;
import com.savethepets.dto.PostInfoDTO;
import com.savethepets.dto.PushDTO;
import com.savethepets.dto.UserInfoDTO;

import java.io.File;
import java.util.List;

public interface UserService {
	boolean leaveId(String userId);
	boolean updateNickname(String userId, String nickname);
	boolean updatePicture(String userId, File picture);
	boolean registerPush(String userId, PushDTO pushDTO);
	UserInfoDTO getUserInfo(String userId);
	List<PostInfoDTO> getMyPosts(String userId);
	List<MyCommentInfoDTO> getMyComments(String userId);
	List<AlarmInfoDTO> getAlarms(String userId);
	List<PostInfoDTO> getBookmarks(String userId);
	
}
