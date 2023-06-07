package com.savethepets.service;

import com.savethepets.dto.UpdateCommentDTO;
import com.savethepets.entity.Alarm;
import com.savethepets.entity.Comment;
import com.savethepets.entity.Post;
import com.savethepets.repository.AlarmRepository;
import com.savethepets.repository.CommentRepository;
import com.savethepets.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final AlarmRepository alarmRepository;
    private final PostRepository postRepository;

    @Override
    public boolean createComment(Comment comment) {
        commentRepository.save(comment);
        // Comment가 속한 Post 객체를 가져옴
        Post post = comment.getPost();
        // 가져온 Post 객체의 postId와 userId를 사용하여 receiverId를 찾음
        String receiverId = postRepository.findOne(post.getPostId()).getUserId();
        // Alarm 객체 생성 및 저장
        alarmRepository.save(new Alarm(comment.getUserId(), receiverId, post.getPostId(), LocalDateTime.now(), 4));
        return true;
    }

    @Override
    public boolean updateComment(UpdateCommentDTO updateCommentDTO) {
        Comment existingComment;
        // DB에 CommentId에 해당하는 record가 있는 경우
        if((existingComment = commentRepository.findOne(updateCommentDTO.getCommentId()))!=null)
        {
            existingComment.setContent(updateCommentDTO.getContent());
            commentRepository.save(existingComment);
            return true;
        }
        // DB에 CommentId에 해당하는 record가 없는 경우
        else
            return false;
    }

    @Override
    public boolean removeComment(Long commentId) {
        Comment comment;
        // DB에 CommentId에 해당하는 record가 있는 경우
        if((comment = commentRepository.findOne(commentId))!=null)
        {
            commentRepository.remove(comment);
            return true;
        }
        // DB에 CommentId에 해당하는 record가 없는 경우
        else
            return false;
    }
}
