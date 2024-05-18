package com.hypnos.Hypnos.repositories;

import com.hypnos.Hypnos.models.Publication;
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
    List<Publication> findPublicationByUser_Id(Long id);
    List<Publication> findPublicationByUser_Alias(String alias);

    // Custom query to find publications by category IDs
    @Query("SELECT p FROM Publication p JOIN p.categories c WHERE c.id IN :categoryIds GROUP BY p.id HAVING COUNT(c.id) = :count")
    List<Publication> findPublicationsByCategoryIds(@Param("categoryIds") List<Long> categoryIds, @Param("count") Long count);

    // Custom query to find liked publications by user ID
    @Query("SELECT p FROM Publication p JOIN p.likedByUsers u WHERE u.id = :userId")
    List<Publication> findLikedPublicationsByUserId(@Param("userId") Long userId);

    // Consulta para obtener un conjunto de IDs al azar
    @Query(value = "SELECT id FROM Publication ORDER BY RAND() LIMIT ?1", nativeQuery = true)
    List<Long> findRandomPublicationIds(int limit);

    // Método para recuperar publicaciones por un conjunto de IDs
    Page<Publication> findByIdIn(List<Long> ids, Pageable pageable);
}

