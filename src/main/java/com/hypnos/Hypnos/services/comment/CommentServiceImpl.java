package com.hypnos.Hypnos.services.comment;

import com.hypnos.Hypnos.dtos.comment.CommentRequestDto;
import com.hypnos.Hypnos.mappers.CommentMapper;
import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.LikeComment;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.repositories.CommentRepository;
import com.hypnos.Hypnos.repositories.LikedCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final LikedCommentRepository likedCommentRepository;


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
    @Transactional
    public void deleteById(Long id) {
        likedCommentRepository.deleteByCommentId(id);
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteCommentsByPublicationId(Long publicationId) {
        likedCommentRepository.deleteByCommentIdInPublication(publicationId);
        commentRepository.deleteByPublicationId(publicationId);
    }
    @Override
    @Transactional
    public void deleteCommentsByUserId(Long userId) {
        commentRepository.deleteByUserId(userId);
    }
    @Override
    @Transactional
    public void deleteCommentsByIds(List<Long> commentIds) {
        for (Long id : commentIds) {
            likedCommentRepository.deleteByCommentId(id);
        }
        List<Comment> comments = commentRepository.findAllById(commentIds);
        if (!comments.isEmpty()) {
            commentRepository.deleteAll(comments);
        }
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
    @Override
    @Transactional
    public void likeComment(Long userId, Long commentId) {
        if (!likedCommentRepository.existsByUserIdAndCommentId(userId, commentId)) {
            likedCommentRepository.save(LikeComment.builder().user(User.builder().id(userId).build()).comment(Comment.builder().id(commentId).build()).build());
        }
    }
    @Override
    @Transactional
    public void dislikeComment(Long userId, Long commentId) {
        if (likedCommentRepository.existsByUserIdAndCommentId(userId, commentId)) {
            likedCommentRepository.deleteByUserIdAndCommentId(userId, commentId);
        }
    }
    @Override
    public long getLikesCount(Long commentId) {
        return commentRepository.countLikesByCommentId(commentId);
    }

    @Override
    public long countCommentsByPublicationId(Long publicationId) {
        return commentRepository.countByPublicationId(publicationId);
    }


}
