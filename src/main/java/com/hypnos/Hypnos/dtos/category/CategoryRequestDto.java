package com.hypnos.Hypnos.dtos.category;

import lombok.*;


@Data
@AllArgsConstructor
@Builder
public class CategoryRequestDto {
    private String name;
    private String description;

}
