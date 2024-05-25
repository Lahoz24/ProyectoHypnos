package com.hypnos.Hypnos.services.publication;

import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;

import java.util.List;

public interface PublicationService {
    List<Publication> findAll();
    Publication findById(Long id);
    List<Publication> findPublicationByText(String text);
    List<Publication> findPublicationByUserId(Long id);
    List<Publication> findPublicationByUserAlias(String alias);
    List<Publication> findPublicationsByCategoryIds(List<Long> categoryIds);
    List<Publication> findLikedPublicationsByUserId(Long userId);
    void deleteById(Long id,String alias);
    Publication save(Publication publication);

    List<Publication> getPublicationsByCategoryIds(List<Long> categoryIds);

    List<Publication> getRandomPublications(int page, int size);
    List<Publication> findByUserInOrderByCreatedAtDesc(List<User> followedUsers);
    List<Publication> getPublicationsFromFollowedUsersOrderByCreatedAtDesc(Long userId);
    Publication updateCategories(Long publicationId, List<Long> categoryIds, Long userId);

}
