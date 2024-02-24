package com.hypnos.Hypnos.services.Comment;

import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.user.User;
import com.hypnos.Hypnos.repositories.CommentRepository;
import com.hypnos.Hypnos.repositories.PublicationRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment findCommentById(UUID uuid) {
        return commentRepository.findById(uuid).orElseThrow();
    }

    @Override
    public List<Comment> findCommentsByUser(User user) {
        return commentRepository.findCommentByUser(user);

    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(UUID uuid, Comment comment) {
        Comment updated = this.findCommentById(uuid);

        updated.setText(comment.getText());
        updated.setChildComments(comment.getChildComments());
        updated.setParentComment(comment.getParentComment());
        updated.setUser(comment.getUser());
        updated.setPublication(comment.getPublication());

        return commentRepository.save(updated);
    }

    @Override
    public void deleteComment(UUID uuid) { commentRepository.deleteById(uuid);}

    @Override
    public List<Comment> getAllComments() { return commentRepository.findAll(); }

}

