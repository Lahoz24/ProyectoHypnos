package com.hypnos.Hypnos.repositories;

import com.hypnos.Hypnos.models.LikePublication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikedPublicationRepository extends JpaRepository<LikePublication, Long> {
    boolean existsByUserIdAndPublicationId(Long userId, Long publicationId);
    void deleteByUserIdAndPublicationId(Long userId, Long publicationId);
    void deleteByPublicationId(Long publicationId);
    List<LikePublication> findByUserId(Long userId);

}

