package com.hypnos.Hypnos.repositories;

import com.hypnos.Hypnos.models.LikePublication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedPublicationRepository extends JpaRepository<LikePublication, Long> {
    boolean existsByUserIdAndPublicationId(Long userId, Long publicationId);
    void deleteByUserIdAndPublicationId(Long userId, Long publicationId);
}

