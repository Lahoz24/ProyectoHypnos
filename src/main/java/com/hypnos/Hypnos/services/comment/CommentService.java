package com.hypnos.Hypnos.services.comment;

import com.hypnos.Hypnos.models.Comment;

import java.util.List;

public interface CommentService {
    Comment findById (Long id);
    List<Comment> findCommentByText(String text);
    List<Comment> findCommentByUserId(Long userId);
    List<Comment> findCommentByPublicationId(Long publicationId);


}
