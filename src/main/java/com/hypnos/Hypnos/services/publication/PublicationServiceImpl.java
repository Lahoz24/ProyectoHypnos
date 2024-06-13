package com.hypnos.Hypnos.services.publication;

import com.hypnos.Hypnos.dtos.publication.PublicationRequestDto;
import com.hypnos.Hypnos.models.LikePublication;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.repositories.CommentRepository;
import com.hypnos.Hypnos.repositories.LikedPublicationRepository;
import com.hypnos.Hypnos.repositories.PublicationRepository;
import com.hypnos.Hypnos.mappers.PublicationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;
    private final PublicationMapper publicationMapper;
    private final CommentRepository commentRepository;
    private final LikedPublicationRepository likedPublicationRepository;



    @Override
    public List<Publication> findAll() {
        return publicationRepository.findAll();
    }

    @Override
    public Publication findById(Long id) {
        return publicationRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Publication> findPublicationByText(String text) {
        return publicationRepository.findPublicationByTextContainsIgnoreCase(text);
    }

    @Override
    public List<Publication> findPublicationByUserId(Long userId) {
        return publicationRepository.findPublicationByUser_Id(userId);
    }

    @Override
    public List<Publication> findPublicationByUserAlias(String alias) {
        return publicationRepository.findPublicationByUser_Alias(alias);
    }

    @Override
    public List<Publication> findPublicationsByCategoryIds(List<Long> categoryIds) {
        return publicationRepository.findPublicationsByCategories_IdIn(categoryIds);
    }

    @Override
    public void deleteById(Long id) {
        // Primero, elimina los comentarios asociados a la publicación
        commentRepository.deleteByPublicationId(id);

        // Luego, elimina la publicación
        publicationRepository.deleteById(id);
    }
    @Override
    public Publication create(PublicationRequestDto publicationRequestDto) {
        Publication publication = publicationMapper.toModel(publicationRequestDto);
        return publicationRepository.save(publication);
    }
    @Override
    public Publication save(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public List<Publication> findRandomPublications() {
        return publicationRepository.findRandomPublications();
    }

    @Override
    @Transactional
    public void likePublication(Long userId, Long publicationId) {
        if (!likedPublicationRepository.existsByUserIdAndPublicationId(userId, publicationId)) {
            likedPublicationRepository.save(LikePublication.builder().user(User.builder().id(userId).build()).publication(Publication.builder().id(publicationId).build()).build());
        }
    }
    @Override
    @Transactional
    public void dislikePublication(Long userId, Long publicationId) {
        if (likedPublicationRepository.existsByUserIdAndPublicationId(userId, publicationId)) {
            likedPublicationRepository.deleteByUserIdAndPublicationId(userId, publicationId);
        }
    }
    @Override
    public long getLikesCount(Long publicationId) {
        return publicationRepository.countLikesByPublicationId(publicationId);
    }


}
