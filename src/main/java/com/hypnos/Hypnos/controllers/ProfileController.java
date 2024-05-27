package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.repositories.UserDetailsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.hypnos.Hypnos.models.User;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserDetailsRepository userDetailsRepository;

    public ProfileController(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userDetailsRepository.findByEmail(email);
        return ResponseEntity.ok(user);
    }
}