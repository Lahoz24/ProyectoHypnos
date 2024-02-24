package com.hypnos.Hypnos.services.Publication;

import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.user.User;
import com.hypnos.Hypnos.repositories.PublicationRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PublicationServiceImpl implements PublicationService{

    private final PublicationRespository publicationRespository;
    @Autowired

    public PublicationServiceImpl(PublicationRespository publicationRespository) {
        this.publicationRespository = publicationRespository;
    }

    @Override
    public Publication findPublicationById(UUID id) {
        return publicationRespository.findById(id).orElseThrow();
    }

    @Override
    public List<Publication> findPublicationsByUser(User user) {
        return publicationRespository.findPublicationByUser(user);
    }

    @Override
    public Publication addPublication(Publication publication) {
        return publicationRespository.save(publication);
    }

    @Override
    public Publication updatePublication(UUID id, Publication publication) {
        Publication updated = this.findPublicationById(id);

        updated.setTitle(publication.getTitle());
        updated.setDescription(publication.getDescription());
        updated.setContainsAudio(publication.getContainsAudio());
        updated.setContainsImage(publication.getContainsImage());
        updated.setContainsText(publication.getContainsText());

        return publicationRespository.save(updated);
    }


    @Override
    public void deletePublication(UUID uuid) {
        publicationRespository.deleteById(uuid);
    }

    @Override
    public List<Publication> getAllPublications() {
        return publicationRespository.findAll();
    }
}
