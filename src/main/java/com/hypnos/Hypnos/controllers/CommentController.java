package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.dtos.Comments.CommentRequestDto;
import com.hypnos.Hypnos.dtos.Comments.CommentResponseDto;
import com.hypnos.Hypnos.mappers.CommentMapper;
import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.user.User;
import com.hypnos.Hypnos.services.Comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @GetMapping("")
    public ResponseEntity<List<CommentResponseDto>> getAllComments(
    ) {
        log.info("getAllComments");

        return ResponseEntity.ok(
                commentMapper.toResponse(commentService.getAllComments())
        );
    }

    @GetMapping("/by-user/{userUUID}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByUserUUID(
            @PathVariable UUID userUUID
    ){
        return ResponseEntity.ok(
                commentMapper.toResponse(commentService.findCommentsByUserUUID(userUUID))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(
            @PathVariable UUID id
    ) {
        log.info("getCommentById");
        return ResponseEntity.ok(
                commentMapper.toResponse(commentService.findCommentById(id))
        );
    }

    @PostMapping
    public ResponseEntity<CommentResponseDto> postComment(
            @RequestBody CommentRequestDto commentRequestDto
    ) {
        log.info("addComment");
        Comment commentSaved = commentService.addComment(commentMapper.toModel(commentRequestDto));
        return ResponseEntity.created(null).body(
                commentMapper.toResponse(commentSaved)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> putComment(
            @PathVariable UUID id,
            @RequestBody CommentRequestDto commentRequestDto
    ) {
        log.info("putComment");
        Comment commentUpdated = commentService.updateComment(id, commentMapper.toModel(commentRequestDto));
        return ResponseEntity.ok(
                commentMapper.toResponse(commentUpdated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponseDto> deleteComment(
            @PathVariable UUID id
    ) {
        log.info("deleteComment");
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<CommentResponseDto> patchComment(
            @PathVariable UUID id,
            @RequestBody CommentRequestDto commentRequestDto
    ) {
        log.info("patchGeneralComment");

        Comment commentPatched = commentService.patch(id, commentMapper.toModel(commentRequestDto));

        return ResponseEntity.ok(commentMapper.toResponse(commentPatched));
    }

}
