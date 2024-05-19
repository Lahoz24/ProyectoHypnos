
package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.services.comment.CommentService;
import com.hypnos.Hypnos.services.comment.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;



    @DeleteMapping("/{userId}/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long userId, @PathVariable Long commentId) {
        // Verificar si el comentario pertenece al usuario
        Comment comment = commentServiceImpl.findById(commentId);
        if (comment != null && comment.getUser().getId().equals(userId)) {
            // El comentario pertenece al usuario, se puede eliminar
            commentServiceImpl.deleteById(commentId);
            return ResponseEntity.noContent().build();
        } else {
            // El comentario no pertenece al usuario, devolver un error de autorización
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}


