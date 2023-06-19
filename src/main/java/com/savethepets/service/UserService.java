package com.savethepets.service;

import com.savethepets.dto.AlarmInfoDTO;
import com.savethepets.dto.MyCommentInfoDTO;
import com.savethepets.dto.PostInfoDTO;
import com.savethepets.dto.PushDTO;
import com.savethepets.dto.UserInfoDTO;

import java.io.File;
import java.util.List;
/**
 * Description<br>
 *  - UserService Interface<br>
 * <br>
 * Method <br>
 *  - leaveId : 사용자를 삭제하는 메소드 <br> 
 *  - updateNickname : 사용자의 닉네임을 갱신하는 메소드 <br>
 *  - updatePicture : 사용자의 프로필사진을 갱신하는 메소드 <br>
 *  - registerPush : 사용자의 Push알림 정보를 등록하는 메소드 <br>
 *  - getUserInfo : 사용자의 정보를 반환하는 메소드 <br>
 *  - getMyPosts : 사용자가 작성한 게시물을 반환하는 메소드 <br>
 *  - getMyComments : 사용자가 작성한 댓글을 반환하는 메소드 <br>
 *  - getAlarms : 사용자가 수신한 알람을 반환하는 메소드 <br>
 *  - getBookmarks : 사용자가 북마크한 게시물을 반환하는 메소드 <br>
 * @author Yuseung lee.
 * @since 2023.06.19
 */
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
