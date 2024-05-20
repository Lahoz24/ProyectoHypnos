package com.hypnos.Hypnos.auth;

import lombok.Data;

@Data
public class SignupRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String alias;
    private String password;
}
