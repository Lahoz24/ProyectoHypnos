package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.auth.LoginRequest;
import com.hypnos.Hypnos.auth.JwtService;
import com.hypnos.Hypnos.auth.SignupRequest;
import com.hypnos.Hypnos.dtos.user.UserResponseDto;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.services.user.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userDetailsService;

    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager, UserServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticateUser(loginRequest.getEmail(), loginRequest.getPassword()));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDetails> signup(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(
                userDetailsService.create(signupRequest) //pasar por mapper
        );
    }

    private String authenticateUser(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtService.createToken(userDetails.getUsername());
    }

    private UserResponseDto mapToUserResponseDto(UserDetails userDetails) {
        // Convierte UserDetails a tu clase de modelo de usuario
        User user = (User) userDetails;

        // Crea una instancia de UserResponseDto y establece los campos con los valores correspondientes
        return UserResponseDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .alias(user.getAlias())
                .email(user.getEmail())
                .role(user.getRole().toString()) // Suponiendo que el campo role es de tipo Enum
                .publications(user.getPublications()) // Ajusta según la estructura real de tu usuario
                .likedPublications(user.getLikedPublications()) // Ajusta según la estructura real de tu usuario
                .likedComments(user.getLikedComments()) // Ajusta según la estructura real de tu usuario
                .following(user.getFollowing()) // Ajusta según la estructura real de tu usuario
                .followers(user.getFollowers()) // Ajusta según la estructura real de tu usuario
                .createdAt(user.getCreatedAt())
                .build();
    }
}

