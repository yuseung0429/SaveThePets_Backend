package com.savethepets.service;

import com.savethepets.dto.UpdateCommentDTO;
import com.savethepets.entity.Comment;

public interface CommentService {

    boolean createComment(Comment comment);
    boolean updateComment(UpdateCommentDTO updateCommentDTO);
    boolean removeComment(Long commentId);

}
