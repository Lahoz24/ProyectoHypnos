package com.hypnos.Hypnos.repositories;

import com.hypnos.Hypnos.models.PublicationLike;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<PublicationLike, UUID>{
    Optional<PublicationLike> findLikeByUuid(UUID uuid);
    List<PublicationLike> findLikesByUser(User user);
    List<PublicationLike> findLikesByPublication(UUID publication);
}
