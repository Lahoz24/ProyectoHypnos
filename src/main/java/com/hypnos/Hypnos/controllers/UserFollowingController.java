package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.models.UserFollowing;
import com.hypnos.Hypnos.services.user.UserFollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
@CrossOrigin(origins = "*")

public class UserFollowingController {

    @Autowired
    private UserFollowingService userFollowingService;

    @PostMapping("/{followerId}/{followedId}")
    public UserFollowing followUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        User follower = new User(); // Debes obtener el usuario real desde tu servicio de usuarios
        follower.setId(followerId);
        User followed = new User(); // Debes obtener el usuario real desde tu servicio de usuarios
        followed.setId(followedId);
        return userFollowingService.followUser(follower, followed);
    }

    @DeleteMapping("/{followerId}/{followedId}")
    public void unfollowUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        User follower = new User(); // Debes obtener el usuario real desde tu servicio de usuarios
        follower.setId(followerId);
        User followed = new User(); // Debes obtener el usuario real desde tu servicio de usuarios
        followed.setId(followedId);
        userFollowingService.unfollowUser(follower, followed);
    }

    @GetMapping("/followers/{userId}")
    public List<UserFollowing> getFollowers(@PathVariable Long userId) {
        User user = new User(); // Debes obtener el usuario real desde tu servicio de usuarios
        user.setId(userId);
        return userFollowingService.getFollowers(user);
    }

    @GetMapping("/following/{userId}")
    public List<UserFollowing> getFollowedUsers(@PathVariable Long userId) {
        User user = new User(); // Debes obtener el usuario real desde tu servicio de usuarios
        user.setId(userId);
        return userFollowingService.getFollowedUsers(user);
    }
}
