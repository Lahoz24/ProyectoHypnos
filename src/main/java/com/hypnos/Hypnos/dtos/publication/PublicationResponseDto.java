package com.hypnos.Hypnos.dtos.publication;

import com.hypnos.Hypnos.models.Category;
import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.User;
import lombok.*;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PublicationResponseDto {
    private final Long id;
    private String title;
    private String text;
    private final User user;
    private Category category;
    private final LocalDateTime createdAt;
}

