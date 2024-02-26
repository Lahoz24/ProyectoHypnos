package com.hypnos.Hypnos.mappers;

import com.hypnos.Hypnos.dtos.User.UserRequestDto;
import com.hypnos.Hypnos.dtos.User.UserResponseDto;
import com.hypnos.Hypnos.models.user.Role;
import com.hypnos.Hypnos.models.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toResponse(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    public User toModel(UserRequestDto userDTO) {
        return new User(
                userDTO.getEmail(),
                userDTO.getPassword()
        );
    }
}
