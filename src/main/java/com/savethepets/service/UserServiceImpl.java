package com.savethepets.service;

import com.savethepets.dto.AlarmInfoDTO;
import com.savethepets.dto.MyCommentInfoDTO;
import com.savethepets.dto.PostInfoDTO;
import com.savethepets.dto.PushDTO;
import com.savethepets.dto.TokenInfoDTO;
import com.savethepets.dto.UserInfoDTO;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.savethepets.repository.TimelineRepository;
import com.savethepets.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final PostPictureRepository postPictureRepository;
	private final CommentRepository commentRepository;
	private final AlarmRepository alarmRepository;
	private final BookmarkRepository bookmarkRepository;

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

	@Override
	public boolean updateNickname(String userId, String nickname) {
		if(userRepository.findByNickname(nickname).isEmpty())
		{
			User temp = userRepository.findOne(userId);
			temp.setNickname(nickname);
			userRepository.save(temp);
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean updatePicture(String userId, byte[] picture) {
		User temp = userRepository.findOne(userId);
		temp.setPicture(picture);
		userRepository.save(temp);
		return true;
	}
	
	@Override
	public boolean registerPush(String userId, PushDTO pushDTO){
		User temp = userRepository.findOne(userId);
		temp.setAuth(pushDTO.getAuth());
		temp.setP256dh(pushDTO.getP256dh());
		temp.setEndpoint(pushDTO.getEndpoint());
		userRepository.save(temp);
		return true;
	}

	@Override
	public UserInfoDTO getUserInfo(String userId) {
		User temp = userRepository.findOne(userId);
		if(temp != null)
			return new UserInfoDTO(temp);
		else
			return null;
	}

	@Override
	public List<PostInfoDTO> getMyPosts(String userId) {
		List<Post> posts = postRepository.findByUserId(userId);
		if(!posts.isEmpty())
		{
			List<PostInfoDTO> postInfos = new ArrayList<PostInfoDTO>();
			for(Post i : posts)
			{
				PostPicture temp = postPictureRepository.findOne(new PostPictureId(i.getPostId(), 0));
				postInfos.add(new PostInfoDTO(i,temp));
			}
			postInfos.sort((a,b)->b.getTimestamp().compareTo(a.getTimestamp()));
			return postInfos;
		}
		else
			return null;
	}

	@Override
	public List<MyCommentInfoDTO> getMyComments(String userId) {
		List<Comment> comments = commentRepository.findByUserId(userId);
		if(!comments.isEmpty())
		{
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
		else
			return null;
	}

	@Override
	public List<AlarmInfoDTO> getAlarms(String userId) {
		List<Alarm> alarms = alarmRepository.findByUserId(userId);
		if(!alarms.isEmpty())
		{
			List<AlarmInfoDTO> alarmInfos = new ArrayList<AlarmInfoDTO>();
			for(Alarm i : alarms)
			{
				// Alarm type이 Post인 경우
				if(i.getType() != Alarm.COMMENT)
				{
					Post tempPost = postRepository.findOne(i.getPostId());
					PostPicture tempPostPicture = postPictureRepository.findOne(new PostPictureId(i.getPostId(), 0));
					alarmInfos.add(new AlarmInfoDTO(i,tempPost,tempPostPicture));
				}
				// Alarm type이 Comment인 경우
				else
				{
					User tempUser = userRepository.findOne(i.getSenderId());
					alarmInfos.add(new AlarmInfoDTO(i,tempUser));
				}
			}
			alarmInfos.sort((a,b)->b.getTimestamp().compareTo(a.getTimestamp()));
			return alarmInfos;
		}
		else
			return null;
	}

	@Override
	public List<PostInfoDTO> getBookmarks(String userId) {
		List<Bookmark> bookmarks = bookmarkRepository.findByUserId(userId);
		if(!bookmarks.isEmpty())
		{
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
		else
			return null;
	}
}
