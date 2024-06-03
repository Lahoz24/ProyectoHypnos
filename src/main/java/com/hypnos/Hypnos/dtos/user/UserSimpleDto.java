package com.hypnos.Hypnos.dtos.user;

import lombok.*;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class UserSimpleDto {
    private final Long id;
    private final String firstname;
    private final String lastname;
    private final String alias;
}
