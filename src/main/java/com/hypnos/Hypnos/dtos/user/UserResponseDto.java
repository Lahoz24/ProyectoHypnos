package com.hypnos.Hypnos.dtos.user;

import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Builder
public class UserResponseDto {
    private final Long id;
    private final String firstname;
    private final String lastname;
    private final String alias;
    private final String email;
    private final String role;
    private final List<Publication> publications;
    private final List<Publication> likedPublications;
    private final List<Comment> likedComments;
    private final List<UserSimpleDto> following;  // Use a simplified DTO
    private final List<UserSimpleDto> followers;  // Use a simplified DTO
    private final LocalDateTime createdAt;
}

