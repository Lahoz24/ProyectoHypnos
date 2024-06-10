package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.models.UserFollowing;
import com.hypnos.Hypnos.services.user.UserFollowingService;
import com.hypnos.Hypnos.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
@CrossOrigin(origins = "*")

public class UserFollowingController {

    @Autowired
    private UserFollowingService userFollowingService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/{followerId}/{followedId}")
    public UserFollowing followUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        User follower = userService.findById(followerId);
        User followed = userService.findById(followedId);
        return userFollowingService.followUser(follower, followed);
    }
    @DeleteMapping("/{followerId}/{followedId}")
    public void unfollowUser(@PathVariable Long followerId, @PathVariable Long followedId) {
        User follower = userService.findById(followerId);
        User followed = userService.findById(followedId);
        userFollowingService.unfollowUser(follower, followed);
    }

    @GetMapping("/followers/{userId}")
    public List<UserFollowing> getFollowers(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return userFollowingService.getFollowers(user);
    }

    @GetMapping("/following/{userId}")
    public List<UserFollowing> getFollowedUsers(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return userFollowingService.getFollowedUsers(user);
    }
}
