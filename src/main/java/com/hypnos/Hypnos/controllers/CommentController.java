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
@CrossOrigin(origins = "http://localhost:4200/")
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

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByCategory(
            @PathVariable User user
    ){
        return ResponseEntity.ok(
                commentMapper.toResponse(commentService.findCommentsByUser(user))
        );
    }

//    @GetMapping("/by-tobuy/{toBuy}")
//    public ResponseEntity<List<CommentResponseDto>> getCommentsByToBuy(
//            @PathVariable boolean toBuy
//    ){
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(publicationService.findCommentsByToBuy(toBuy))
//        );
//    }

    //    @GetMapping("/by-tocheck/{toCheck}")
//    public ResponseEntity<List<CommentResponseDto>> getCommentsByToCheck(
//            @PathVariable boolean toCheck
//    ){
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(publicationService.findCommentsByToCheck(toCheck))
//        );
//    }
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

//    @PatchMapping("/stock/{id}")
//    public ResponseEntity<CommentResponseDto> setStock(
//            @PathVariable Long id,
//            @RequestParam Double stock
//
//    ) {
//        LocalDateTime stockTimestamp = LocalDateTime.now();
//        log.info("setStock");
//        Comment commentUpdated = publicationService.setStock(id, stock, stockTimestamp);
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(commentUpdated)
//        );
//    }

//    @PatchMapping("/tobuy/{id}")
//    public ResponseEntity<CommentResponseDto> setToBuy(
//            @PathVariable Long id,
//            @RequestParam Boolean toBuy
//    ) {
//        LocalDateTime toBuyTimestamp = LocalDateTime.now();
//        log.info("setToBuy");
//        Comment commentUpdated = publicationService.setToBuy(id, toBuy, toBuyTimestamp);
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(commentUpdated)
//        );
//    }

//    @PatchMapping("/tocheck/{id}")
//    public ResponseEntity<CommentResponseDto> setToCheck(
//            @PathVariable Long id,
//            @RequestParam Boolean toCheck
//
//    ) {
//        LocalDateTime toCheckTimestamp = LocalDateTime.now();
//        log.info("setToCheck");
//        Comment commentUpdated = publicationService.setToCheck(id, toCheck, toCheckTimestamp);
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(commentUpdated)
//        );
//    }

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

//    @GetMapping("/max-stock")
//    public ResponseEntity<List<CommentResponseDto>> getCommentsByMaxStock(
//            @RequestParam Double maxStock
//    ) {
//        log.info("getCommentsByMaxStock");
//
//        if (maxStock < 0) return ResponseEntity.badRequest().build();
//
//        return ResponseEntity.ok(
//                publicationMapper.toResponse(publicationService.findByStockLessThanEqual(maxStock))
//        );
//    }
}
