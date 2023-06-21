package com.savethepets.service;

import com.savethepets.dto.AlarmInfoDTO;
import com.savethepets.dto.MyCommentInfoDTO;
import com.savethepets.dto.PostInfoDTO;
import com.savethepets.dto.PushDTO;
import com.savethepets.dto.UserInfoDTO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savethepets.entity.Alarm;
import com.savethepets.entity.Bookmark;
import com.savethepets.entity.Comment;
import com.savethepets.entity.Post;
import com.savethepets.entity.PostPicture;
import com.savethepets.entity.User;
import com.savethepets.id.PostPictureId;
import com.savethepets.repository.AlarmRepository;
import com.savethepets.repository.BookmarkRepository;
import com.savethepets.repository.CommentRepository;
import com.savethepets.repository.PostPictureRepository;
import com.savethepets.repository.PostRepository;
import com.savethepets.repository.UserRepository;
import com.savethepets.utility.Utilities;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Description<br>
 *  - UserServiceImpl Class : UserService를 구현한 구현체 클래스<br>
 * <br>
 * Field<br>
 * 	- awsService : AWS S3 사용을 위한 Service <br>
 *  - userRepository : User Table 접근 Repository <br>
 *  - postRepository : Post Table 접근 Repository <br>
 *  - postPictureRepository : PostPicture Table 접근 Repository <br>
 *  - commentRepository : Comment Table 접근 Repository <br>
 *  - alarmRepository : Alarm Table 접근 Repository <br>
 *  - bookmarkRepository : Bookmark Table 접근 Repository <br>
 * <br>
 * Method<br>
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
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final AwsServiceImpl awsService;
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final PostPictureRepository postPictureRepository;
	private final CommentRepository commentRepository;
	private final AlarmRepository alarmRepository;
	private final BookmarkRepository bookmarkRepository;
	/**
	 * Description<br>
	 *  - leaveId : 사용자를 삭제하는 메소드 <br> 
	 * @param userId 사용자 Id
	 * @return true/false (삭제 성공 여부) 
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@Override
	public boolean leaveId(String userId) {
		User temp;
		if((temp = userRepository.findOne(userId)) != null)
		{
			userRepository.remove(temp);
			return true;
		}
		else
			return false;
	}
	/**
	 * Description<br>
	 *  - updateNickname : 사용자의 닉네임을 갱신하는 메소드<br>
	 * @param userId 사용자 Id
	 * @param nickname 변경할 닉네임
	 * @return true/false (변경 성공 여부) 
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@Override
	public boolean updateNickname(String userId, String nickname) {
		User temp = userRepository.findOne(userId);
		if(nickname.equals(temp.getNickname()))
			return true;
		else if(userRepository.findByNickname(nickname).isEmpty())
		{
			temp.setNickname(nickname);
			userRepository.save(temp);
			return true;
		}
		else
			return false;
	}
	/**
	 * Description<br>
	 *  - updatePicture : 사용자의 프로필사진을 갱신하는 메소드<br>
	 * @param userId 사용자 Id
	 * @param picture 변경할 프로필사진
	 * @return true/false (변경 성공 여부) 
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@Override
	public boolean updatePicture(String userId, File picture) {
		User temp = userRepository.findOne(userId);
		temp.setPicture(awsService.save(picture, "users/"+userId, "profile", Utilities.getExtension(picture.getName())));
		userRepository.save(temp);
		return true;
	}
	/**
	 * Description<br>
	 *  - updatePicture : 사용자의 프로필사진을 갱신하는 메소드<br>
	 * @param userId 사용자 Id
	 * @param pushDTO ({@link com.savethepets.dto.PushDTO}) Push 등록 정보 
	 * @return true/false (등록 성공 여부) 
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@Override
	public boolean registerPush(String userId, PushDTO pushDTO){
		User temp = userRepository.findOne(userId);
		temp.setAuth(pushDTO.getAuth());
		temp.setP256dh(pushDTO.getP256dh());
		temp.setEndpoint(pushDTO.getEndpoint());
		userRepository.save(temp);
		return true;
	}
	/**
	 * Description<br>
	 *  - getUserInfo : 사용자의 정보를 반환하는 메소드<br>
	 * @param userId 사용자 Id
	 * @return UserInfoDTO ({@link com.savethepets.dto.UserInfoDTO}) 또는 null(존재하지 않을 경우)
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@Override
	public UserInfoDTO getUserInfo(String userId) {
		User temp = userRepository.findOne(userId);
		if(temp != null)
			return new UserInfoDTO(temp);
		else
			return null;
	}
	/**
	 * Description<br>
	 *  - getMyPosts : 사용자가 작성한 게시물을 반환하는 메소드<br>
	 * @param userId 사용자 Id
	 * @return PostInfoDTO ({@link com.savethepets.dto.PostInfoDTO}) List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@Override
	public List<PostInfoDTO> getMyPosts(String userId) {
		List<Post> posts = postRepository.findByUserId(userId);
		List<PostInfoDTO> postInfos = new ArrayList<PostInfoDTO>();
		for(Post i : posts)
		{
			PostPicture temp = postPictureRepository.findOne(new PostPictureId(i.getPostId(), 0));
			postInfos.add(new PostInfoDTO(i,temp));
		}
		postInfos.sort((a,b)->b.getTimestamp().compareTo(a.getTimestamp()));
		return postInfos;
	}
	/**
	 * Description<br>
	 *  - getMyComments : 사용자가 작성한 댓글을 반환하는 메소드<br>
	 * @param userId 사용자 Id
	 * @return MyCommentInfoDTO ({@link com.savethepets.dto.MyCommentInfoDTO}) List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@Override
	public List<MyCommentInfoDTO> getMyComments(String userId) {
		List<Comment> comments = commentRepository.findByUserId(userId);
		List<MyCommentInfoDTO> myCommentInfos = new ArrayList<MyCommentInfoDTO>();
		for(Comment i : comments)
		{
			Post tempPost = postRepository.findOne(i.getPost().getPostId());
			PostPicture tempPostPicture = postPictureRepository.findOne(new PostPictureId(i.getPost().getPostId(), 0));
			myCommentInfos.add(new MyCommentInfoDTO(i,tempPost,tempPostPicture));
		}
		myCommentInfos.sort((a,b)->b.getTimestamp().compareTo(a.getTimestamp()));
		return myCommentInfos;
	}
	/**
	 * Description<br>
	 *  - getAlarms : 사용자가 수신한 알람을 반환하는 메소드<br>
	 * @param userId 사용자 Id
	 * @return MyCommentInfoDTO ({@link com.savethepets.dto.AlarmInfoDTO}) List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@Override
	public List<AlarmInfoDTO> getAlarms(String userId) {
		List<Alarm> alarms = alarmRepository.findByUserId(userId);
		List<AlarmInfoDTO> alarmInfos = new ArrayList<AlarmInfoDTO>();
		for(Alarm i : alarms)
		{
			if(i.getType() != Alarm.COMMENT)
			{
				Post tempPost = postRepository.findOne(i.getPostId());
				PostPicture tempPostPicture = postPictureRepository.findOne(new PostPictureId(i.getPostId(), 0));
				alarmInfos.add(new AlarmInfoDTO(i,tempPost,tempPostPicture));
			}
			else
			{
				User tempUser = userRepository.findOne(i.getSenderId());
				alarmInfos.add(new AlarmInfoDTO(i,tempUser));
			}
		}
		alarmInfos.sort((a,b)->b.getTimestamp().compareTo(a.getTimestamp()));
		return alarmInfos;
	}
	/**
	 * Description<br>
	 *  - getBookmarks : 사용자가 북마크한 게시물을 반환하는 메소드<br>
	 * @param userId 사용자 Id
	 * @return PostInfoDTO ({@link com.savethepets.dto.PostInfoDTO}) List
	 * @author Yuseung lee.
	 * @since 2023.06.19
	 */
	@Override
	public List<PostInfoDTO> getBookmarks(String userId) {
		List<Bookmark> bookmarks = bookmarkRepository.findByUserId(userId);
		List<PostInfoDTO> postInfos = new ArrayList<PostInfoDTO>();
		for(Bookmark i : bookmarks)
		{
			Post tempPost = postRepository.findOne(i.getBookmarkId().getPostId());
			PostPicture tempPostPicture = postPictureRepository.findOne(new PostPictureId(tempPost.getPostId(), 0));
			PostInfoDTO tempPostInfo = new PostInfoDTO(tempPost, tempPostPicture);
			tempPostInfo.setTimestamp(i.getTimestamp());
			postInfos.add(tempPostInfo);
		}
		postInfos.sort((a,b)->b.getTimestamp().compareTo(a.getTimestamp()));
		return postInfos;
	}
}
