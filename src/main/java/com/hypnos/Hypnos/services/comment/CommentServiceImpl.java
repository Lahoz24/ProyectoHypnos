package com.hypnos.Hypnos.services.comment;

import com.hypnos.Hypnos.dtos.comment.CommentRequestDto;
import com.hypnos.Hypnos.mappers.CommentMapper;
import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Comment> findCommentByText(String text) {
        return commentRepository.findCommentByTextContainsIgnoreCase(text);
    }

    @Override
    public List<Comment> findCommentByUserId(Long userId) {
        return commentRepository.findCommentByUser_Id(userId);
    }

    @Override
    public List<Comment> findCommentByPublicationId(Long publicationId) {
        return commentRepository.findCommentByPublication_Id(publicationId);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
    @Override
    public Comment create(CommentRequestDto commentRequestDto) {
        Comment comment = commentMapper.toModel(commentRequestDto);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }


}
