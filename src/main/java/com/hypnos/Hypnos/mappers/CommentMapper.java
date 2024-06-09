package com.hypnos.Hypnos.mappers;

import com.hypnos.Hypnos.dtos.comment.CommentRequestDto;
import com.hypnos.Hypnos.dtos.comment.CommentResponseDto;
import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CommentMapper {
    private final UserMapper userMapper;
    private final PublicationMapper publicationMapper;

    @Autowired
    public CommentMapper(UserMapper userMapper, PublicationMapper publicationMapper) {
        this.userMapper = userMapper;
        this.publicationMapper = publicationMapper;
    }

    public Comment toModel(CommentRequestDto commentRequestDto) {
        return new Comment(
                0L,
                commentRequestDto.getText(),
                commentRequestDto.getUserId() != null ?
                        userMapper.toModelFromRequestDto(commentRequestDto.getUserId()) : null,
                commentRequestDto.getPublicationId() != null ?
                        publicationMapper.toModelFromRequestDto(commentRequestDto.getPublicationId()) : null,
                LocalDateTime.now()
        );
    }

    public Comment toModelFromRequestDto(Long commentId) {
        return new Comment(
                commentId,
                null,
                null,
                null,
                null
        );
    }
    public CommentResponseDto toResponse(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getText(),
                comment.getUser(),
                comment.getPublication(),
                comment.getCreatedAt()
        );
    }

    public List<CommentResponseDto> toResponse(List<Comment> comment) {
        return comment.stream()
                .map(this::toResponse)
                .toList();
    }
}
