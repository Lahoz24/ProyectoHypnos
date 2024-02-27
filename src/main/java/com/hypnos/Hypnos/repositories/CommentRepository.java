package com.hypnos.Hypnos.repositories;


import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Optional<Comment> findCommentByUuid(UUID uuid);
    List<Comment> findCommentByUserUUID(UUID userUUID);
    List<Comment> findCommentByPublication(Publication publication);
}
