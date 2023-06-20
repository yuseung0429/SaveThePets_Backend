package com.savethepets.service;

import com.savethepets.dto.*;
import com.savethepets.entity.Post;

import java.io.File;
import java.util.List;


public interface PostService {
    Long createPost(Post post, List<File> pictures);
    boolean removePost(Long postId);
    boolean updatePost(UpdatePostDTO updatePostDTO);
    List<PostInfoDTO> getBoardPosts();
    List<PostInfoDTO> getMapPosts(DistancePostDTO distancePostDTO);
    List<PostInfoDTO> getFilteredPosts(FilterDTO filterDTO);
    PostDetailedInfoDTO getPostDetail(Long postId);
    List<PostInfoDTO> getMyLostPosts(String userId);
	void sendPostNotification(Long postId);
}
