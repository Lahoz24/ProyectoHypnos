package com.hypnos.Hypnos.repositories;

import com.hypnos.Hypnos.models.Category;
import com.hypnos.Hypnos.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findCommentById(Long id);
    List<Comment> findCommentByTextContainsIgnoreCase(String text);
    List<Comment> findCommentByUser_Id(Long userId);
    List<Comment> findCommentByPublication_Id(Long publicationId);

}
