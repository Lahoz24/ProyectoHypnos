package com.hypnos.Hypnos.repositories;

import com.hypnos.Hypnos.models.Category;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    Optional<Publication> findPublicationById(Long id);
    List<Publication> findPublicationByTextContainsIgnoreCase(String text);
    List<Publication> findPublicationByUser_Id(Long userId);
    List<Publication> findPublicationByUser_Alias(String alias);
    List<Publication> findPublicationsByCategories_IdIn(List<Long> categoryIds);
    @Query(value = "SELECT * FROM publication ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<Publication> findRandomPublications();
    @Query("SELECT COUNT(lp) FROM LikePublication lp WHERE lp.publication.id = :publicationId")
    long countLikesByPublicationId(@Param("publicationId") Long publicationId);

/*    @Query(value = "SELECT * FROM publication ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<Object[]> findRandomPublications();*/

}
