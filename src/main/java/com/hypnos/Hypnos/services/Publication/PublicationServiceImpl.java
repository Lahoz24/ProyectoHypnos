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
    public List<Publication> findPublicationsByUser(Long userId) {
        return publicationRespository.findPublicationsByUserId(userId);
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
    public List<Publication> findAll() {
        return publicationRespository.findAll();
    }

    @Override
    public List<Publication> findPublicationByTitle(String title) {
        return publicationRespository.findPublicationsByTitleContainsIgnoreCase(title);
    }

    @Override
    public Publication patch(UUID id, Publication publication) {

        Publication publicationToPatch = publicationRespository.findById(id).orElseThrow();

        if(publication.getDescription() != null){
            publicationToPatch.setDescription(publication.getDescription());
        }
        if(publication.getContainsText() != null){
            publicationToPatch.setContainsText(publication.getContainsText());
        }
        if(publication.getContainsImage() != null){
            publicationToPatch.setContainsImage(publication.getContainsImage());
        }
        if(publication.getContainsAudio() != null){
            publicationToPatch.setContainsAudio(publication.getContainsAudio());
        }
        if(publication.getTitle() != null){
            publicationToPatch.setTitle(publication.getTitle());
        }
        if(publication.getUser() != null){
            publicationToPatch.setUser(publication.getUser());
        }
        if(publication.getComments() != null){
            publicationToPatch.setComments(publication.getComments());
        }

        return publicationRespository.save(publicationToPatch);
    }

    @Override
    public List<Publication> getAllPublications() {
        return publicationRespository.findAll();
    }
}
