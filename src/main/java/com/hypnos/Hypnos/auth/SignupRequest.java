package com.hypnos.Hypnos.auth;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
}
