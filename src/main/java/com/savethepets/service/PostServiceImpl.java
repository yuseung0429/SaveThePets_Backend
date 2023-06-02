package com.savethepets.service;

import com.savethepets.dto.AnalyzedPictureDTO;
import com.savethepets.dto.FilterDTO;
import com.savethepets.dto.PostDetailedInfoDTO;
import com.savethepets.dto.PostInfoDTO;
import com.savethepets.entity.Post;

import java.util.List;

public class PostServiceImpl implements PostService{

    @Override
    public boolean createPost(Post post) {
        return false;
    }

    @Override
    public boolean removePost(Long postId) {
        return false;
    }

    @Override
    public boolean updatePost(Post post) {
        return false;
    }

    @Override
    public List<PostInfoDTO> getBoardPosts(int start, int end) {
        return null;
    }

    @Override
    public List<PostInfoDTO> getMapPosts(Double userLat, Double userLot) {
        return null;
    }

    @Override
    public List<PostInfoDTO> getFilteredPosts(FilterDTO filter) {
        return null;
    }

    @Override
    public PostDetailedInfoDTO getPostDetail(Long postId) {
        return null;
    }

    @Override
    public List<PostInfoDTO> getMyLostPosts(String userId) {
        return null;
    }

    @Override
    public AnalyzedPictureDTO analyzePictures(List<byte[]> pictures) {
        return null;
    }
}
