package com.hypnos.Hypnos.dtos.category;

import lombok.*;


@Data
@AllArgsConstructor
@Builder
public class CategoryResponseDto {
    private final Long id;
    private final String name;
    private final String description;

}
