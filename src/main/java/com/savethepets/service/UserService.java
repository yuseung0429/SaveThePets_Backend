package com.savethepets.service;

import com.savethepets.dto.AlarmInfoDTO;
import com.savethepets.dto.MyCommentInfoDTO;
import com.savethepets.dto.PostInfoDTO;
import com.savethepets.dto.TokenInfoDTO;
import com.savethepets.dto.UserInfoDTO;
import com.savethepets.entity.User;

import java.util.List;

public interface UserService {
	TokenInfoDTO signup(String kakaoToken);
	boolean leaveId(String userId);
	boolean updateNickname(String userId, String nickname);
	boolean updatePicture(String userId, byte[] picture);
	UserInfoDTO getUserInfo(String userId);
	List<PostInfoDTO> getMyPosts(String userId);
	List<MyCommentInfoDTO> getMyComments(String userId);
	List<AlarmInfoDTO> getAlarms(String userId);
	List<PostInfoDTO> getBookmarks(String userId);

}
