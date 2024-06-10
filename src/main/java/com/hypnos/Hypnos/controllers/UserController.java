package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.auth.SignupRequest;
import com.hypnos.Hypnos.dtos.category.CategoryResponseDto;
import com.hypnos.Hypnos.dtos.user.UserRequestDto;
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
@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final UserMapper userMapper;

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(
    ) {
        log.info("getAllUsers");

        return ResponseEntity.ok(
                userMapper.toResponse(userServiceImpl.findAll())
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        log.info("getUserById");
        User user = userServiceImpl.findById(id);
        return ResponseEntity.ok(userMapper.toResponse(user));
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
    public ResponseEntity<UserResponseDto> patchUser(@PathVariable String alias, @RequestBody UserRequestDto userRequestDto) {
        log.info("patchUser");
        User updatedUser = userServiceImpl.patch(alias, userMapper.toModel(userRequestDto));
        return ResponseEntity.ok(userMapper.toResponse(updatedUser));
    }

}