package com.hypnos.Hypnos.dtos.publication;

import com.hypnos.Hypnos.models.Category;
import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PublicationResponseDto {
    private final Long id;
    private String text;
    private final User user;
    private List<Category> categories;
    private List<Comment> comments;
    private List<User> likedByUsers;
    private final LocalDateTime createdAt;
}
