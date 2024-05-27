package com.hypnos.Hypnos.dtos.publication;

import com.hypnos.Hypnos.models.Category;
import com.hypnos.Hypnos.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PublicationRequestDto {
    private String text;
    private final Long userId;
    private List<Long> categoryIds;
}
