package com.hypnos.Hypnos.services.user;

import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.models.UserFollowing;
import com.hypnos.Hypnos.repositories.UserFollowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserFollowingService {

    @Autowired
    private UserFollowingRepository userFollowingRepository;

    public List<UserFollowing> getFollowedUsers(User user) {
        return userFollowingRepository.findByFollower(user);
    }

    public List<UserFollowing> getFollowers(User user) {
        return userFollowingRepository.findByFollowed(user);
    }

    public UserFollowing followUser(User follower, User followed) {
        UserFollowing userFollowing = new UserFollowing();
        userFollowing.setFollower(follower);
        userFollowing.setFollowed(followed);
        return userFollowingRepository.save(userFollowing);
    }

    public void unfollowUser(User follower, User followed) {
        Optional<UserFollowing> userFollowing = userFollowingRepository.findByFollowerAndFollowed(follower, followed);
        userFollowing.ifPresent(userFollowingRepository::delete);
    }
}
