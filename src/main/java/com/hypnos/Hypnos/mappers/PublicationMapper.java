package com.hypnos.Hypnos.mappers;


import com.hypnos.Hypnos.dtos.publication.PublicationRequestDto;
import com.hypnos.Hypnos.dtos.publication.PublicationResponseDto;
import com.hypnos.Hypnos.models.Category;
import com.hypnos.Hypnos.models.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PublicationMapper {
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;
    private final CategoryMapper categoryMapper;

    @Autowired
    public PublicationMapper(UserMapper userMapper,CommentMapper commentMapper,CategoryMapper categoryMapper){
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
        this.categoryMapper = categoryMapper;
    }
    public PublicationResponseDto toResponse(Publication publication) {
        return new PublicationResponseDto(
                publication.getId(),
                publication.getText(),
                publication.getUser(),
                publication.getCategories(),
                publication.getComments(),
                publication.getLikedByUsers(),
                publication.getCreatedAt()
        );
    }

    // Usar un método que reciba una lista de IDs de categorías y devuelva una lista de modelos de categorías
    public Publication toModel(PublicationRequestDto publicationRequestDto) {
        List<Long> categoryIds = publicationRequestDto.getCategoryIds();
        List<Category> categories = new ArrayList<>();
        if (categoryIds != null) {
            for (Long categoryId : categoryIds) {
                Category category = categoryMapper.toModelfromRequestDto(categoryId);
                categories.add(category);
            }
        }

        return new Publication(
                0L,
                publicationRequestDto.getText(),
                publicationRequestDto.getUserId() != null ?
                        userMapper.toModelfromRequestDto(publicationRequestDto.getUserId()) : null,
                categories, // Utiliza la lista de modelos de categorías creada anteriormente
                null,
                null,
                LocalDateTime.now()
        );
    }

    public Publication toModelfromRequestDto(Long publicationId) {
        return new Publication(
                publicationId,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
