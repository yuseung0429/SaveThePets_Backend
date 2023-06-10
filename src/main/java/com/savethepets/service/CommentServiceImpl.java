package com.savethepets.service;

import com.savethepets.dto.UpdateCommentDTO;

import com.savethepets.entity.Comment;
import com.savethepets.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public boolean createComment(Comment comment) {
        commentRepository.save(comment);
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
