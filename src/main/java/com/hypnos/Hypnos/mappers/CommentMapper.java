package com.hypnos.Hypnos.mappers;

import com.hypnos.Hypnos.dtos.Comments.CommentRequestDto;
import com.hypnos.Hypnos.dtos.Comments.CommentResponseDto;
import com.hypnos.Hypnos.models.Comment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class CommentMapper {

    public CommentResponseDto toResponse(Comment comment) {
        return new CommentResponseDto(
                comment.getUuid(),
                comment.getText(),
                comment.getUser(),
                comment.getPublication(),
                null
        );
    }

    public List<CommentResponseDto> toResponse(List<Comment> comments) {
        return comments.stream()
                .map(this::toResponse)
                .toList();
    }

    public Comment toModel(CommentRequestDto commentRequestDto) {
        return new Comment(
                UUID.randomUUID(),
                commentRequestDto.getText(),
                commentRequestDto.getUser(),
                commentRequestDto.getPublication(),
                null,
                null
        );
    }

}
