package com.hypnos.Hypnos.dtos.publication;

import com.hypnos.Hypnos.dtos.user.UserSimpleDto;
import lombok.*;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class PublicationSimpleDto {
    private final Long id;
    private final String text;
    private final String title;
    private final UserSimpleDto user;
}
