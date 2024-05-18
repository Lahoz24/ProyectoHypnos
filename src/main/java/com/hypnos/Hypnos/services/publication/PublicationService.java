package com.hypnos.Hypnos.services.publication;

import com.hypnos.Hypnos.models.Publication;

import java.util.List;

public interface PublicationService {
    Publication findById(Long id);
    List<Publication> findPublicationByText(String text);
    List<Publication> findPublicationByUserId(Long id);
    List<Publication> findPublicationByUserAlias(String alias);
    List<Publication> findPublicationsByCategoryIds(List<Long> categoryIds);
    List<Publication> findLikedPublicationsByUserId(Long userId);
    void deleteById(Long id);
    Publication save(Publication publication);
    Publication update(Long id, Publication publication);
    List<Publication> getPublicationsByCategoryIds(List<Long> categoryIds);

    List<Publication> getRandomPublications(int page, int size);
}
