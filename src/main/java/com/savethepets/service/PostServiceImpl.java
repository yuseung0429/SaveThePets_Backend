package com.savethepets.service;

import com.savethepets.dto.*;
import com.savethepets.entity.*;
import com.savethepets.id.BookmarkId;
import com.savethepets.id.PostPictureId;
import com.savethepets.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final PostPictureRepository postPictureRepository;
    private final CommentRepository commentRepository;
    private final TimelineRepository timelineRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;

    @Override
    public Long createPost(Post post, List<byte[]> pictures){
        Long postId = postRepository.create(post); // Post 저장하여 postId 생성
        if(pictures!=null){
            //PostPicture에 게시글 사진 저장
            for(int i = 0; i< pictures.size(); i++) {
                postPictureRepository.save(new PostPicture(new PostPictureId(postId, i), pictures.get(i)));
            }
        }
        return postId;
    }

    @Override
    public boolean removePost(Long postId) {
        Post post;
        // DB에 postId에 해당하는 record가 있는 경우
        if((post = postRepository.findOne(postId))!=null)
        {
            postRepository.remove(post);
            return true;
        }
        // DB에 postId에 해당하는 record가 없는 경우
        else
            return false;
    }

    @Override
    public boolean updatePost(UpdatePostDTO updatePostDTO) {
        Post existingPost = postRepository.findOne(updatePostDTO.getPostId());
        if(existingPost != null) {
            existingPost.setContent(updatePostDTO.getContent());
            existingPost.setSpecies(updatePostDTO.getSpecies());
            existingPost.setBreed(updatePostDTO.getBreed());
            existingPost.setType(updatePostDTO.getType());
//            existingPost.setSpeciesAi(post.getSpeciesAi());
//            existingPost.setBreedAi(post.getBreedAi());
//            existingPost.setAccuracy(post.getAccuracy());
            existingPost.setLat(updatePostDTO.getPostLat());
            existingPost.setLot(updatePostDTO.getPostLot());
            existingPost.setAddress(updatePostDTO.getAddress());
            existingPost.setTime(updatePostDTO.getTime());

            postRepository.save(existingPost);

            // 기존에 첨부된 사진 삭제
            List<PostPicture> existingPictures = postPictureRepository.findByPostId(updatePostDTO.getPostId());
            if (existingPictures != null) {
                for (PostPicture picture : existingPictures) {
                    postPictureRepository.remove(picture);
                }
            }
            // 사진 다시 등록
            List<byte[]> pictures = updatePostDTO.getPictures();
            if (pictures != null) {
                for (int i = 0; i < pictures.size(); i++) {
                    postPictureRepository.save(new PostPicture(new PostPictureId(updatePostDTO.getPostId(), i), pictures.get(i)));
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<PostInfoDTO> getBoardPosts() {
        List<Post> posts = postRepository.findAllPosts(); // 모든 게시물 가져오기
        List<PostInfoDTO> postInfos = new ArrayList<PostInfoDTO>();
        for(Post i : posts) {
            PostPicture temp = postPictureRepository.findOne(new PostPictureId(i.getPostId(), 0));
            postInfos.add(new PostInfoDTO(i,temp));
        }
        postInfos.sort((a,b)->b.getTimestamp().compareTo(a.getTimestamp()));
        return postInfos;
    }

    @Override
    public List<PostInfoDTO> getMapPosts(Double userLat, Double userLot) {
        return null;
    }

    @Override
    public List<PostInfoDTO> getFilteredPosts(FilterDTO filterDTO) {
        List<Post> filteredPosts = postRepository.findFilteredPosts(filterDTO);//  필터링된 게시물 가져오기
        List<PostInfoDTO> postInfos = new ArrayList<PostInfoDTO>();
        for(Post i : filteredPosts) {
            PostPicture temp = postPictureRepository.findOne(new PostPictureId(i.getPostId(), 0));
            postInfos.add(new PostInfoDTO(i,temp));
        }
        return postInfos;
    }

    @Override
    public PostDetailedInfoDTO getPostDetail(Long postId) {
        Post post = postRepository.findOne(postId);
        if(post != null){
            User user = userRepository.findOne(post.getUserId());
            //북마크 정보 넣기
            BookmarkId bookmarkId = new BookmarkId(post.getUserId(),postId);
            Bookmark bookmark = bookmarkRepository.findOne(bookmarkId);
            boolean bookmarked = (bookmark!=null);
            //게시물 사진들 넣기
            List<PostPicture> postPictures = postPictureRepository.findByPostId(postId);
            //게시물 댓글 넣기
            List<Comment> comments = commentRepository.findByPostId(postId);
            List<CommentInfoDTO> commentInfoDTOs = new ArrayList<>();
            for(Comment comment : comments){
                String userId = comment.getUserId();
                User user2 = userRepository.findOne(userId);
                CommentInfoDTO commentInfoDTO = new CommentInfoDTO(comment,user2);
                commentInfoDTOs.add(commentInfoDTO);
            }
            //게시물 타임라인 넣기
            List<Timeline> timelines = timelineRepository.findByMissingPostId(postId);
            List<TimelineInfoDTO> timelineInfoDTOs = new ArrayList<>();
            for(Timeline timeline : timelines){
                Post sightingpost = postRepository.findOne(timeline.getTimelineId().getSightingPostId());
                PostPictureId postPictureId = new PostPictureId(timeline.getTimelineId().getSightingPostId(),0);
                byte[] thumbnail = postPictureRepository.findOne(postPictureId).getPicture();
                TimelineInfoDTO timelineInfoDTO = new TimelineInfoDTO(sightingpost,thumbnail);
                timelineInfoDTOs.add(timelineInfoDTO);
            }
            PostDetailedInfoDTO postDetailedInfoDTO = new PostDetailedInfoDTO(post,user,postPictures, bookmarked, commentInfoDTOs, timelineInfoDTOs);

            return postDetailedInfoDTO;
        }else{
            return null;
        }
    }


    @Override
    public List<PostInfoDTO> getMyLostPosts(String userId) {
        List<Post> lostPosts = postRepository.findLostPostsByUserId(userId); // 사용자의 분실 게시물 가져오기
        List<PostInfoDTO> postInfos = new ArrayList<PostInfoDTO>();
        for(Post i : lostPosts) {
            PostPicture temp = postPictureRepository.findOne(new PostPictureId(i.getPostId(), 0));
            postInfos.add(new PostInfoDTO(i,temp));
        }
        postInfos.sort((a,b)->b.getTimestamp().compareTo(a.getTimestamp()));
        return postInfos;
    }


    @Override
    public AnalyzedPictureDTO analyzePictures(List<byte[]> pictures) {
        return null;
    }
}
