package com.hypnos.Hypnos.repositories;

import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PublicationRespository extends JpaRepository<Publication, UUID> {

    Optional<Publication> findPublicationByUuid(UUID uuid);
    List<Publication> findPublicationByUser(User user);
    List<Publication> findPublicationByTitle(String title);
}
