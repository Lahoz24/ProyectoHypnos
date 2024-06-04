package com.hypnos.Hypnos.services.publication;

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
    List<Publication> findPublicationByUserId(Long id);
    List<Publication> findPublicationByUserAlias(String alias);
    List<Publication> findPublicationsByCategoryIds(List<Long> categoryIds);
    List<Publication> findLikedPublicationsByUserId(Long userId);
    void deleteById(Long id);
    Publication save(Publication publication);


    List<Publication> findByUserInOrderByCreatedAtDesc(List<User> followedUsers);
    List<Publication> getPublicationsFromFollowedUsersOrderByCreatedAtDesc(Long userId);
    Publication updateCategories(Long publicationId, List<Long> categoryIds, Long userId);
    List<Object[]> findRandomPublications();

    List<Publication> getPublicationsByCategoryId(Long categoryId);

}