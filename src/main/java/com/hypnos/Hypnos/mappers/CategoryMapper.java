package com.hypnos.Hypnos.mappers;



import com.hypnos.Hypnos.dtos.category.CategoryRequestDto;
import com.hypnos.Hypnos.dtos.category.CategoryResponseDto;
import com.hypnos.Hypnos.models.Category;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {


    public Category toModel(CategoryRequestDto categoryRequestDto) {
        return new Category(
                0L,
                categoryRequestDto.getName(),
                categoryRequestDto.getDescription()
        );
    }

    public Category toModelFromRequestDto(Long categoryId) {
        return new Category(
                categoryId,
                null,
                null
        );
    }
    public CategoryResponseDto toResponse(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public List<CategoryResponseDto> toResponse(List<Category> categories) {
        return categories.stream()
                .map(this::toResponse)
                .toList();
    }
}

