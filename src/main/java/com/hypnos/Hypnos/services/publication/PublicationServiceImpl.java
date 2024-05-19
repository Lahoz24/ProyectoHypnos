package com.hypnos.Hypnos.services.publication;

import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.repositories.PublicationRepository;
import com.hypnos.Hypnos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository, UserRepository userRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Publication findById(Long id) {
        return null;
    }

    @Override
    public List<Publication> findPublicationByText(String text) {
        return null;
    }

    @Override
    public List<Publication> findPublicationByUserId(Long id) {
        return null;
    }

    @Override
    public List<Publication> findPublicationByUserAlias(String alias) {
        return null;
    }

    @Override
    public List<Publication> findPublicationsByCategoryIds(List<Long> categoryIds) {
        return null;
    }

    @Override
    public List<Publication> findLikedPublicationsByUserId(Long userId) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Publication save(Publication publication) {
        return null;
    }

    @Override
    public Publication update(Long id, Publication publication) {
        return null;
    }

    @Override
    public List<Publication> getPublicationsByCategoryIds(List<Long> categoryIds) {
        return null;
    }

    @Override
    public List<Publication> getRandomPublications(int page, int size) {
        return null;
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
}
