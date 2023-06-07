package com.savethepets.service;

import com.savethepets.dto.AlarmInfoDTO;
import com.savethepets.dto.MyCommentInfoDTO;
import com.savethepets.dto.PostInfoDTO;
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
import com.savethepets.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final PostRepository postRepository;
	@Autowired
	private final PostPictureRepository postPictureRepository;
	@Autowired
	private final CommentRepository commentRepository;
	@Autowired
	private final AlarmRepository alarmRepository;
	@Autowired
	private final BookmarkRepository bookmarkRepository;

	@Override
	public TokenInfoDTO signup(String kakaoToken) {
		return null;
	}

	@Override
	public boolean leaveId(String userId) {
		User temp;
		// DB에 userId에 해당하는 record가 있는 경우
		if((temp = userRepository.findOne(userId)) != null)
		{
			userRepository.remove(temp);
			return true;
		}
		// DB에 userId에 해당하는 record가 없는 경우
		else
			return false;
	}

	@Override
	public boolean updateNickname(String userId, String nickname) {
		// DB에 해당 nickname을 사용하는 user가 없는 경우
		if(userRepository.findByNickname(nickname).isEmpty())
		{
			User temp = userRepository.findOne(userId);
			temp.setNickname(nickname);
			userRepository.save(temp);
			return true;
		}
		// DB에 해당 nickname을 사용하는 user가 있는 경우
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
	public UserInfoDTO getUserInfo(String userId) {
		User temp = userRepository.findOne(userId);
		// DB에 userId에 해당하는 record가 없는 경우
		if(temp != null)
			return new UserInfoDTO(temp);
		// DB에 userId에 해당하는 record가 있는 경우
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
	
	public void registerUser(String userId, String nickname, byte[] picture)
	{
		User temp1 = userRepository.findOne(userId);
		List<User> temp2 = userRepository.findByNickname(nickname);
		if (temp1 == null && temp2 == null)
		{
			if(picture == null)
			{
				byte[] bytes = {0x01};
				userRepository.save(new User(userId, nickname, bytes));
			}
			else
				userRepository.save(new User(userId, nickname, picture));
		}
	}
}
