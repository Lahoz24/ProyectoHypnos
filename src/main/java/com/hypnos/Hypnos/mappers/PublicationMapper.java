package com.hypnos.Hypnos.mappers;

import com.hypnos.Hypnos.dtos.category.CategoryResponseDto;
import com.hypnos.Hypnos.dtos.publication.PublicationRequestDto;
import com.hypnos.Hypnos.dtos.publication.PublicationResponseDto;
import com.hypnos.Hypnos.models.Category;
import com.hypnos.Hypnos.models.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublicationMapper {
    private final CategoryMapper categoryMapper;
    private final UserMapper userMapper;

    @Autowired
    public PublicationMapper(UserMapper userMapper, CategoryMapper categoryMapper) {
        this.userMapper = userMapper;
        this.categoryMapper = categoryMapper;
    }

    public Publication toModel(PublicationRequestDto publicationRequestDto) {
        return new Publication(
                0L,
                publicationRequestDto.getText(),
                publicationRequestDto.getTitle(),
                publicationRequestDto.getUserId() != null ?
                        userMapper.toModelFromRequestDto(publicationRequestDto.getUserId()) : null,
                publicationRequestDto.getCategoryId() != null ?
                        categoryMapper.toModelFromRequestDto(publicationRequestDto.getCategoryId()) : null,
                LocalDateTime.now()
        );
    }


    public Publication toModelFromRequestDto(Long publicationId) {
        return new Publication(
                publicationId,
                null,
                null,
                null,
                null,
                null
        );
    }
    public PublicationResponseDto toResponse(Publication publication) {
        return new PublicationResponseDto(
                publication.getId(),
                publication.getTitle(),
                publication.getText(),
                publication.getUser(),
                publication.getCategory(),
                publication.getCreatedAt()
        );
    }

    public List<PublicationResponseDto> toResponse(List<Publication> publications) {
        return publications.stream()
                .map(this::toResponse)
                .toList();
    }

}