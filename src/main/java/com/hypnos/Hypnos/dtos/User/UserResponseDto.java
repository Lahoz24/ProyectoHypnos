package com.hypnos.Hypnos.dtos.User;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Builder
public class UserResponseDto {
    private final Long id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String role;
}
