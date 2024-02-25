package com.hypnos.Hypnos.services.Comment;

import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.user.User;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    Comment findCommentById(UUID uuid);

    List<Comment> findCommentsByUser(User user);

    Comment addComment(Comment comment);

    Comment updateComment(UUID uuid, Comment comment);

    void deleteComment(UUID uuid);

    List<Comment> getAllComments();

    Comment patch(UUID id, Comment comment);

}
