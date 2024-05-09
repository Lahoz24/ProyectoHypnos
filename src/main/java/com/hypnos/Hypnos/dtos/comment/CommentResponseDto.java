package com.hypnos.Hypnos.dtos.comment;

import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CommentResponseDto {
    private final Long id;
    private String text;
    private List<User> likedByUsers;
    private User user;
    private Publication publication;
    private LocalDateTime createdAt;
}
