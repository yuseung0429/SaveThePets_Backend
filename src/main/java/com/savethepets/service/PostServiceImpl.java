package com.savethepets.service;

import com.savethepets.dto.AnalyzedPictureDTO;
import com.savethepets.dto.FilterDTO;
import com.savethepets.dto.PostDetailedInfoDTO;
import com.savethepets.dto.PostInfoDTO;
import com.savethepets.entity.Post;
import com.savethepets.entity.PostPicture;
import com.savethepets.id.PostPictureId;
import com.savethepets.repository.PostPictureRepository;
import com.savethepets.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final PostPictureRepository postPictureRepository;


    @Override
    public boolean createPost(Post post, List<byte[]> pictures){
        try{
            Long postId = postRepository.create(post); // Post 저장하여 postId 생성
            if(pictures!=null){
                for(int i = 0; i< pictures.size(); i++) {
                    postPictureRepository.save(new PostPicture(new PostPictureId(postId, i), pictures.get(i)));
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean removePost(Long postId) {
        try {
            Post post = postRepository.findOne(postId);
            if (post != null) {
                postRepository.remove(post);
                return true;
            }
        } catch (Exception e) {

        }
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
