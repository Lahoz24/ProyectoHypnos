package com.hypnos.Hypnos.dtos.category;

import lombok.*;


@Data
@AllArgsConstructor
@Builder
public class CategoryRequestDto {
    private final String name;
    private final String description;

}
