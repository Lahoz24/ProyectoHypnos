package com.hypnos.Hypnos.services.comment;

import com.hypnos.Hypnos.dtos.comment.CommentRequestDto;
import com.hypnos.Hypnos.models.Category;
import com.hypnos.Hypnos.models.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CommentService {
    Comment findById (Long id);
    List<Comment> findCommentByText(String text);
    List<Comment> findCommentByUserId(Long userId);
    List<Comment> findCommentByPublicationId(Long publicationId);
    Comment save(Comment comment);
    Comment create(CommentRequestDto commentRequestDto);
    void deleteById(Long id);
    List<Comment> findAll();
}
