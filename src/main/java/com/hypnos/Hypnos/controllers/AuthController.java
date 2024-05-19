package com.hypnos.Hypnos.controllers;

import com.hypnos.Hypnos.auth.LoginRequest;
import com.hypnos.Hypnos.auth.JwtService;
import com.hypnos.Hypnos.auth.SignupRequest;
import com.hypnos.Hypnos.services.user.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
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
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                ));
        return ResponseEntity.ok(
                jwtService.createToken(authentication.getName()
                )
        );
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDetails> signup(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(
//                jwtService.createToken(authentication.getName())
                userDetailsService.create(signupRequest) //pasar por mapper
        );
    }
}

