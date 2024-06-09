package com.hypnos.Hypnos.dtos.publication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PublicationRequestDto {
    private String title;
    private String text;
    private Long userId;
    private Long categoryId;
}
