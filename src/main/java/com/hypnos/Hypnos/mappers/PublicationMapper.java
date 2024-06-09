package com.hypnos.Hypnos.mappers;

import com.hypnos.Hypnos.dtos.publication.PublicationRequestDto;
import com.hypnos.Hypnos.dtos.publication.PublicationResponseDto;
import com.hypnos.Hypnos.models.Category;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.repositories.CategoryRepository;
import com.hypnos.Hypnos.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PublicationMapper {

    private final UserDetailsRepository userDetailsRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PublicationMapper(UserDetailsRepository userDetailsRepository, CategoryRepository categoryRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.categoryRepository = categoryRepository;
    }

    public Publication toModel(PublicationRequestDto publicationRequestDto) {
        User user = userDetailsRepository.findById(publicationRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Category> categories = publicationRequestDto.getCategoryIds().stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Category not found")))
                .collect(Collectors.toList());

        return new Publication(
                null,
                publicationRequestDto.getText(),
                publicationRequestDto.getTitle(),
                user,
                categories,
                LocalDateTime.now()
        );
    }

    public PublicationResponseDto toResponse(Publication publication) {
        List<Category> categories = publication.getCategories();

        return new PublicationResponseDto(
                publication.getId(),
                publication.getTitle(),
                publication.getText(),
                publication.getUser(),
                categories,
                publication.getCreatedAt()
        );
    }


    public List<PublicationResponseDto> toResponse(List<Publication> publications) {
        return publications.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
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
}
