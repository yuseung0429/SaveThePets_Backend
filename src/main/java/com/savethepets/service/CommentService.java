package com.savethepets.service;

import com.savethepets.entity.Comment;

public interface CommentService {

    boolean createComment(Comment comment);
    boolean updateComment(Comment comment);
    boolean removeComment(Comment comment);

}
