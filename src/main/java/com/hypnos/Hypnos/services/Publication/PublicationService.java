package com.hypnos.Hypnos.services.Publication;

import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublicationService {

    Publication findPublicationById(UUID uuid);

    List<Publication> findPublicationsByUser(User user);

    Publication addPublication(Publication publication);

    Publication updatePublication(UUID uuid, Publication publication);

    void deletePublication(UUID uuid);

    List<Publication> getAllPublications();

}
