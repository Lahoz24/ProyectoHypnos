package com.hypnos.Hypnos.repositories;

import com.hypnos.Hypnos.models.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikedCommentRepository extends JpaRepository<LikeComment, Long> {
    boolean existsByUserIdAndCommentId(Long userId, Long commentId);
    void deleteByUserIdAndCommentId(Long userId, Long commentId);
    void deleteByCommentId(Long commentId);
    @Modifying
    @Query("DELETE FROM LikeComment lc WHERE lc.comment.id IN (SELECT c.id FROM Comment c WHERE c.publication.id = :publicationId)")
    void deleteByCommentIdInPublication(Long publicationId);
}
