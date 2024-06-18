package com.hypnos.Hypnos.services.comment;

import com.hypnos.Hypnos.dtos.comment.CommentRequestDto;
import com.hypnos.Hypnos.models.Comment;

import java.util.List;

public interface CommentService {
    Comment findById (Long id);
    List<Comment> findCommentByText(String text);
    List<Comment> findCommentByUserId(Long userId);
    List<Comment> findCommentByPublicationId(Long publicationId);
    Comment save(Comment comment);
    Comment create(CommentRequestDto commentRequestDto);
    void deleteById(Long id);
    void deleteCommentsByPublicationId(Long publicationId);
    void deleteCommentsByUserId(Long userId);
    void deleteCommentsByIds(List<Long> commentIds);
    List<Comment> findAll();
    void likeComment(Long userId, Long commentId);
    void dislikeComment(Long userId, Long commentId);
    long getLikesCount(Long commentId);
    long countCommentsByPublicationId(Long publicationId);


}
