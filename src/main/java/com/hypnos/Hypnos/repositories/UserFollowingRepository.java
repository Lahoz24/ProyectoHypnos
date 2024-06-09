package com.hypnos.Hypnos.repositories;

import com.hypnos.Hypnos.models.UserFollowing;
import com.hypnos.Hypnos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserFollowingRepository extends JpaRepository<UserFollowing, Long> {
    List<UserFollowing> findByFollower(User follower);
    List<UserFollowing> findByFollowed(User followed);
    Optional<UserFollowing> findByFollowerAndFollowed(User follower, User followed);

}
