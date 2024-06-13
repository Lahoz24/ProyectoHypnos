package com.hypnos.Hypnos.repositories;

import com.hypnos.Hypnos.models.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedCommentRepository extends JpaRepository<LikeComment, Long> {
    boolean existsByUserIdAndCommentId(Long userId, Long commentId);
    void deleteByUserIdAndCommentId(Long userId, Long commentId);
}
