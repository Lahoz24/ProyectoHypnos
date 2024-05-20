package com.hypnos.Hypnos.services.publication;

import com.hypnos.Hypnos.models.Category;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.repositories.CategoryRepository;
import com.hypnos.Hypnos.repositories.PublicationRepository;
import com.hypnos.Hypnos.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService {
    private final PublicationRepository publicationRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public Publication findById(Long id) {
        return publicationRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Publication> findPublicationByText(String text) {
        return publicationRepository.findPublicationByTextContainsIgnoreCase(text);
    }

    @Override
    public List<Publication> findPublicationByUserId(Long id) {
        return publicationRepository.findPublicationByUser_Id(id);
    }

    @Override
    public List<Publication> findPublicationByUserAlias(String alias) {
        return publicationRepository.findPublicationByUser_Alias(alias);
    }

    @Override
    public List<Publication> findPublicationsByCategoryIds(List<Long> categoryIds) {
        return publicationRepository.findPublicationsByCategoryIds(categoryIds);
    }

    @Override
    public List<Publication> findLikedPublicationsByUserId(Long userId) {
        return publicationRepository.findLikedPublicationsByUserId(userId);
    }

    @Override
    @PreAuthorize("#alias == authentication.principal.alias")
    public void deleteById(Long id, String alias) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publication not found"));
        if (publication.getUser().getAlias().equals(alias)) {
            publicationRepository.deleteById(id);
        } else {
            throw new AccessDeniedException("You are not allowed to delete this publication");
        }
    }

    @Override
    @PreAuthorize("#publication.user.alias == authentication.principal.alias")
    public Publication save(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public List<Publication> getPublicationsByCategoryIds(List<Long> categoryIds) {
        return publicationRepository.findPublicationsByCategoryIds(categoryIds);
    }

    @Override
    public List<Publication> getRandomPublications(int page, int size) {
        // Seleccionar un conjunto de IDs al azar
        List<Long> randomIds = publicationRepository.findRandomPublicationIds(size);
        // Recuperar las publicaciones correspondientes a esos IDs
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Publication> publicationPage = publicationRepository.findByIdIn(randomIds, pageRequest);
        return publicationPage.getContent();
    }

    @Override
    public List<Publication> findByUserInOrderByCreatedAtDesc(List<User> followedUsers) {
        return publicationRepository.findByUserInOrderByCreatedAtDesc(followedUsers);
    }

    @Override
    public List<Publication> getPublicationsFromFollowedUsersOrderByCreatedAtDesc(Long userId) {
        // Obtenemos al usuario
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Obtenemos las publicaciones de los usuarios que sigue el usuario
        List<User> followedUsers = user.getFollowing();
        return publicationRepository.findByUserInOrderByCreatedAtDesc(followedUsers);
    }

    @Override
    @PreAuthorize("#userId == authentication.principal.id")
    public Publication updateCategories(Long publicationId, List<Long> categoryIds, Long userId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new IllegalArgumentException("Publication not found"));
        if (!publication.getUser().getId().equals(userId)) {
            throw new IllegalStateException("User is not the owner of the publication");
        }

        List<Category> categories = categoryRepository.findAllById(categoryIds);
        publication.setCategories(categories);
        return publicationRepository.save(publication);
    }
}