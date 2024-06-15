package com.hypnos.Hypnos.services.publication;

import com.hypnos.Hypnos.dtos.publication.PublicationRequestDto;
import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface PublicationService {
    List<Publication> findAll();
    Publication findById(Long id);
    List<Publication> findPublicationByText(String text);
    List<Publication> findPublicationByUserId(Long userId);
    List<Publication> findPublicationByUserAlias(String alias);
    List<Publication> findPublicationsByCategoryIds(List<Long> categoryIds);

    void deleteById(Long id);
    Publication create(PublicationRequestDto PublicationRequestDto);

    Publication save(Publication Publication);

    List<Publication> findRandomPublications();
    void likePublication(Long userId, Long publicationId);
    void dislikePublication(Long userId, Long publicationId);
    long getLikesCount(Long publicationId);
    List<Publication> getLikedPublicationsByUserId(Long userId);
}