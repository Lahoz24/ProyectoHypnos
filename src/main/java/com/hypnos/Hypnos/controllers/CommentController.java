package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.dtos.category.CategoryResponseDto;
import com.hypnos.Hypnos.dtos.comment.CommentRequestDto;
import com.hypnos.Hypnos.dtos.comment.CommentResponseDto;
import com.hypnos.Hypnos.dtos.like.LikeRequestDto;
import com.hypnos.Hypnos.mappers.CommentMapper;
import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.services.comment.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;
    private final CommentMapper commentMapper;

    @GetMapping("")
    public ResponseEntity<List<CommentResponseDto>> getAllComments(
    ) {
        log.info("getAllComments");

        return ResponseEntity.ok(
                commentMapper.toResponse(commentServiceImpl.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = commentServiceImpl.findById(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Comment>> searchCommentByText(@RequestParam String text) {
        List<Comment> comments = commentServiceImpl.findCommentByText(text);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable Long userId) {
        List<Comment> comments = commentServiceImpl.findCommentByUserId(userId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/publication/{publicationId}")
    public ResponseEntity<List<Comment>> getCommentByPublicationId(@PathVariable Long publicationId) {
        List<Comment> comments = commentServiceImpl.findCommentByPublicationId(publicationId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto CommentRequestDto) {
        try{
            Comment createdComment = commentServiceImpl.create(CommentRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper.toResponse(createdComment));
        }catch (Exception e) {
            log.error("Error while creating comment: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long id) {
        commentServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/like")
    public ResponseEntity<Void> likeOrDislikeComment(@PathVariable Long id, @RequestBody LikeRequestDto likeRequestDto) {
        if (likeRequestDto.isLike()) {
            commentServiceImpl.likeComment(likeRequestDto.getUserId(), id);
        } else {
            commentServiceImpl.dislikeComment(likeRequestDto.getUserId(), id);
        }
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}/likes")
    public ResponseEntity<Long> getLikesCount(@PathVariable Long id) {
        try {
            long likesCount = commentServiceImpl.getLikesCount(id);
            return ResponseEntity.ok(likesCount);
        } catch (Exception e) {
            log.error("Error while fetching likes count for comment ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}