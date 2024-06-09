package com.hypnos.Hypnos.services.publication;

import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface PublicationService {
    List<Publication> findAll();
    Publication findById(Long id);
    List<Publication> findPublicationByText(String text);
    List<Publication> findPublicationByUserId(Long userId);
    List<Publication> findPublicationByUserAlias(String alias);
    List<Publication> findPublicationByCategoryId(Long categoryId);
    void deleteById(Long id);
    Publication save(Publication publication);
    List<Publication> findRandomPublications();



}