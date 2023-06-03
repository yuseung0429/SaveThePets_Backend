package com.savethepets.service;

import com.savethepets.dto.AlarmInfoDTO;
import com.savethepets.dto.MyCommentInfoDTO;
import com.savethepets.dto.PostInfoDTO;
import com.savethepets.dto.TokenInfoDTO;
import com.savethepets.dto.UserInfoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.savethepets.entity.Post;
import com.savethepets.entity.PostPicture;

import com.savethepets.repository.AlarmRepository;
import com.savethepets.repository.BookmarkRepository;
import com.savethepets.repository.CommentRepository;
import com.savethepets.repository.PostPictureRepository;
import com.savethepets.repository.PostRepository;
import com.savethepets.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	public TokenInfoDTO signup(String kakaoToken) {
		return null;
	}

	@Override
	public boolean leaveId(String userId) {
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
		List<Long> postIds = bookmarkRepository.findPostIdsByUserId(userId);
		Collections.sort(postIds);
		
		List<Post> posts = postRepository.findByPostIds(postIds);
		List<PostPicture> postpictures = postPictureRepository.findByPostIds(postIds);
		List<PostInfoDTO> postInfos = new ArrayList<PostInfoDTO>();
		
		for(int i=0; i<posts.size(); i++)
			postInfos.add(new PostInfoDTO(posts.get(i), postpictures.get(i)));
		
		postInfos.sort(new Comparator<PostInfoDTO>() {
			@Override
			public int compare(PostInfoDTO o1, PostInfoDTO o2) {
				return o2.getTime().compareTo(o1.getTime());
			}
		});
		return postInfos;
	}




}
