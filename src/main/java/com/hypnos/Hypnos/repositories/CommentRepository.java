package com.hypnos.Hypnos.repositories;


import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Optional<Comment> findCommentByUuid(UUID uuid);
    List<Comment> findCommentByUser(User user);
    List<Comment> findCommentByPublication(Publication publication);
}
