package com.hypnos.Hypnos.mappers;


import com.hypnos.Hypnos.dtos.user.UserRequestDto;
import com.hypnos.Hypnos.dtos.user.UserResponseDto;
import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import jakarta.persistence.ManyToMany;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserMapper {
    public UserResponseDto toResponse(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getRole().name(),
                user.getPublications(),
                user.getLikedPublications(),
                user.getLikedComments(),
                user.getCreatedAt()
        );
    }

    public User toModel(UserRequestDto userRequestDto) {
        return new User(
                0L,
                userRequestDto.getFirstname(),
                userRequestDto.getLastname(),
                userRequestDto.getEmail(),
                userRequestDto.getPassword(),
                userRequestDto.getRole(),
                null,
                null,
                null,
                LocalDateTime.now()
        );
    }
    public User toModelfromRequestDto(Long userId) {
        return new User(
                userId,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
