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
@CrossOrigin(origins = "http://127.0.0.1:5173")
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
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            String token = jwtService.createToken(authentication.getName());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            // Loggear el error
            System.err.println("Error en autenticación: " + e.getMessage());
            // Devolver un estado 403 con un mensaje detallado
            return ResponseEntity.status(403).body("Correo electrónico o contraseña incorrectos.");
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDetails> signup(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(
//                jwtService.createToken(authentication.getName())
                userDetailsService.create(signupRequest) //pasar por mapper
        );
    }
}

