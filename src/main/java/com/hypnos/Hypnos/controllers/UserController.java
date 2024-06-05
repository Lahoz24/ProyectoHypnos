package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.auth.SignupRequest;
import com.hypnos.Hypnos.dtos.user.UserRequestDto;
import com.hypnos.Hypnos.dtos.user.UserResponseDto;
import com.hypnos.Hypnos.mappers.UserMapper;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.services.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final UserMapper userMapper;

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        log.info("getUserById");
        User user = userServiceImpl.findById(id);
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    @GetMapping("/{alias}/following")
    public ResponseEntity<List<UserResponseDto>> getFollowing(@PathVariable String alias) {
        log.info("getFollowing");
        User user = userServiceImpl.findByAlias(alias);
        List<User> following = userServiceImpl.getFollowing(user.getId());
        return ResponseEntity.ok(userMapper.toResponse(following));
    }

    @GetMapping("/{alias}/followers")
    public ResponseEntity<List<UserResponseDto>> getFollowers(@PathVariable String alias) {
        log.info("getFollowers");
        User user = userServiceImpl.findByAlias(alias);
        List<User> followers = userServiceImpl.getFollowers(user.getId());
        return ResponseEntity.ok(userMapper.toResponse(followers));
    }


    @GetMapping("/{alias}/following-users")
    public ResponseEntity<List<UserResponseDto>> getFollowingUsers(@PathVariable String alias) {
        log.info("getFollowingUsers");
        User user = userServiceImpl.findByAlias(alias);
        List<User> followingUsers = userServiceImpl.findByFollowing_Id(user.getId());
        return ResponseEntity.ok(userMapper.toResponse(followingUsers));
    }

    @GetMapping("/{alias}/followers-users")
    public ResponseEntity<List<UserResponseDto>> getFollowersUsers(@PathVariable String alias) {
        log.info("getFollowersUsers");
        User user = userServiceImpl.findByAlias(alias);
        List<User> followersUsers = userServiceImpl.findByFollowers_Id(user.getId());
        return ResponseEntity.ok(userMapper.toResponse(followersUsers));
    }

    @GetMapping("/{alias}/publications-count")
    public ResponseEntity<Long> getPublicationsCount(@PathVariable String alias) {
        log.info("getPublicationsCount");
        User user = userServiceImpl.findByAlias(alias);
        return ResponseEntity.ok(userServiceImpl.countPublications(user.getId()));
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody SignupRequest signupRequest) {
        log.info("createUser");
        User createdUser = userServiceImpl.create(signupRequest);
        return ResponseEntity.ok(userMapper.toResponse(createdUser));
    }

    @DeleteMapping("/{alias}")
    public ResponseEntity<Void> deleteUserByAlias(@PathVariable String alias) {
        log.info("deleteUserByAlias");
        userServiceImpl.deleteByAlias(alias);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/follow/{followId}")
    public ResponseEntity<UserResponseDto> followUser(@PathVariable Long userId, @PathVariable Long followId) {
        UserResponseDto updatedUser = userServiceImpl.followUser(userId, followId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}/unfollow/{userToUnfollowId}") // Cambiar los parámetros de alias a id
    public ResponseEntity<Void> unfollowUser(@PathVariable Long id, @PathVariable Long userToUnfollowId) {
        log.info("unfollowUser");
        userServiceImpl.unfollowUser(id, userToUnfollowId); // Utilizar los IDs directamente
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{alias}")
    public ResponseEntity<UserResponseDto> getByAlias(@PathVariable String alias) {
        log.info("getByAlias");
        User user = userServiceImpl.findByAlias(alias);
        if (user != null) {
            return ResponseEntity.ok(userMapper.toResponse(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{alias}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String alias, @RequestBody UserRequestDto userRequestDto) {
        log.info("updateUser");
        try {
            User updatedUser = userServiceImpl.updateUser(alias, userRequestDto);
            return ResponseEntity.ok(userMapper.toResponse(updatedUser));
        } catch (Exception e) {
            log.error("Error updating user: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}