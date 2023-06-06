package com.savethepets.service;

import com.savethepets.dto.AnalyzedPictureDTO;
import com.savethepets.dto.FilterDTO;
import com.savethepets.dto.PostDetailedInfoDTO;
import com.savethepets.dto.PostInfoDTO;
import com.savethepets.entity.Post;

import java.util.List;


public interface PostService {
    Long createPost(Post post, List<byte[]> pictures);
    boolean removePost(Long postId);
    boolean updatePost(Post post, List<byte[]> pictures);
    List<PostInfoDTO> getBoardPosts(int start, int end);
    List<PostInfoDTO> getMapPosts(Double userLat, Double userLot);
    List<PostInfoDTO> getFilteredPosts(FilterDTO filter);
    PostDetailedInfoDTO getPostDetail(Long postId);
    List<PostInfoDTO> getMyLostPosts(String userId);
    AnalyzedPictureDTO analyzePictures(List<byte[]> pictures);

}
