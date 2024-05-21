package com.hypnos.Hypnos.mappers;

import com.hypnos.Hypnos.dtos.comment.CommentRequestDto;
import com.hypnos.Hypnos.dtos.comment.CommentResponseDto;
import com.hypnos.Hypnos.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CommentMapper {
    private final UserMapper userMapper;
    private final PublicationMapper publicationMapper;

    @Autowired
    public CommentMapper(UserMapper userMapper, @Lazy PublicationMapper publicationMapper) {
        this.userMapper = userMapper;
        this.publicationMapper = publicationMapper;
    }

    public CommentResponseDto toResponse(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getText(),
                comment.getLikedByUsers(),
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

    // Mapeamos de DTO a modelo
    public Comment toModel(CommentRequestDto commentRequestDto) {
        return new Comment(
                0L,
                commentRequestDto.getText(),
                null,
                commentRequestDto.getUserId() != null ?
                        userMapper.toModelfromRequestDto(commentRequestDto.getUserId()) : null,
                commentRequestDto.getPublicationId() != null ?
                        publicationMapper.toModelfromRequestDto(commentRequestDto.getPublicationId()) : null,
                LocalDateTime.now()
        );
    }
}
