package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.dtos.user.UserResponseDto;
import com.hypnos.Hypnos.mappers.UserMapper;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.services.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}/following")
    public ResponseEntity<List<UserResponseDto>> getFollowing(@PathVariable Long id) {
        log.info("getFollowing");
        List<User> following = userService.getFollowing(id);
        return ResponseEntity.ok(userMapper.toResponse(following));
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<List<UserResponseDto>> getFollowers(@PathVariable Long id) {
        log.info("getFollowers");
        List<User> followers = userService.getFollowers(id);
        return ResponseEntity.ok(userMapper.toResponse(followers));
    }

    @GetMapping("/{id}/follow-stats")
    public ResponseEntity<UserResponseDto> getUserFollowStats(@PathVariable Long id) {
        log.info("getUserFollowStats");
        return ResponseEntity.ok(userService.getUserFollowStats(id));
    }
}
