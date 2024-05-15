package com.hypnos.Hypnos.dtos.category;

import lombok.*;


@Data
@AllArgsConstructor
@Builder
public class CategoryResponseDto {
    private final Long id;
    private String name;
    private String description;

}
