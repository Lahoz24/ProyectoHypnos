package com.hypnos.Hypnos.mappers;

import com.hypnos.Hypnos.dtos.user.UserRequestDto;
import com.hypnos.Hypnos.dtos.user.UserResponseDto;
import com.hypnos.Hypnos.models.Role;
import com.hypnos.Hypnos.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toModel(UserRequestDto userRequestDto) {
        return new User(
                0L,
                userRequestDto.getFirstname(),
                userRequestDto.getLastname(),
                userRequestDto.getAlias(),
                userRequestDto.getEmail(),
                userRequestDto.getPassword(),
                Role.USER,
                LocalDateTime.now()
        );
    }

    public User toModelFromRequestDto(Long userId) {
        return new User(
                userId,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public UserResponseDto toResponse(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getAlias(),
                user.getEmail(),
                user.getRole().name(),
                user.getCreatedAt()
        );
    }
    public List<UserResponseDto> toResponse(List<User> users) {
        return users.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
