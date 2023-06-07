package com.savethepets.service;

import com.savethepets.dto.*;
import com.savethepets.entity.Post;

import java.util.List;


public interface PostService {
    Long createPost(Post post, List<byte[]> pictures);
    boolean removePost(Long postId);
    boolean updatePost(UpdatePostDTO updatePostDTO);
    List<PostInfoDTO> getBoardPosts();
    List<PostInfoDTO> getMapPosts(Double userLat, Double userLot);
    List<PostInfoDTO> getFilteredPosts(FilterDTO filterDTO);
    PostDetailedInfoDTO getPostDetail(Long postId);
    List<PostInfoDTO> getMyLostPosts(String userId);
    AnalyzedPictureDTO analyzePictures(List<byte[]> pictures);

}
