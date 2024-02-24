package com.hypnos.Hypnos.services.Like;

import com.hypnos.Hypnos.models.PublicationLike;
import com.hypnos.Hypnos.models.user.User;
import com.hypnos.Hypnos.repositories.LikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public PublicationLike findLikeById(UUID uuid) {
        return likeRepository.findById(uuid).orElseThrow();
    }

    @Override
    public List<PublicationLike> findLikesByUser(User user) {
        return likeRepository.findLikesByUser(user);

    }

    @Override
    public PublicationLike addLike(PublicationLike like) {
        return likeRepository.save(like);
    }

    @Override
    public PublicationLike updateLike(UUID uuid, PublicationLike like) {
        PublicationLike updated = this.findLikeById(uuid);

        updated.setPublication(like.getPublication());
        updated.setUser(like.getUser());

        return likeRepository.save(updated);
    }

    @Override
    public void deleteLike(UUID uuid) { likeRepository.deleteById(uuid);}

    @Override
    public List<PublicationLike> getAllLikes() { return likeRepository.findAll(); }

}

