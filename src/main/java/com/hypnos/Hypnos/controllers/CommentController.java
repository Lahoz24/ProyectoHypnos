package com.hypnos.Hypnos.controllers;

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
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

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
    public ResponseEntity<List<Comment>> getCommentsByPublicationId(@PathVariable Long publicationId) {
        List<Comment> comments = commentServiceImpl.findCommentByPublicationId(publicationId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment createdComment = commentServiceImpl.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long id) {
        commentServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
