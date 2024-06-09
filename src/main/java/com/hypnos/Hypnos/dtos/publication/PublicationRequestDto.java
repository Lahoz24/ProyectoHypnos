package com.hypnos.Hypnos.dtos.publication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class PublicationRequestDto {
    private String title;
    private String text;
    private Long userId;
    private List<Long> categoryIds;
}
