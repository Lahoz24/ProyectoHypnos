package com.hypnos.Hypnos.services.Like;

import com.hypnos.Hypnos.models.PublicationLike;
import com.hypnos.Hypnos.models.user.User;

import java.util.List;
import java.util.UUID;

public interface LikeService {

    PublicationLike findLikeById(UUID uuid);

    List<PublicationLike> findLikesByUser(User user);

    PublicationLike addLike(PublicationLike like);

    PublicationLike updateLike(UUID uuid, PublicationLike like);

    void deleteLike(UUID uuid);

    List<PublicationLike> getAllLikes();

}
