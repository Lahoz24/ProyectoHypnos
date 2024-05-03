package com.hypnos.Hypnos.services.user;

import com.hypnos.Hypnos.auth.SignupRequest;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.repositories.user.UserDetailsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDetailsRepository.findByEmail(email);
    }
    public List<User> getAll(){
        return userDetailsRepository.findAll();
    }
    public UserDetails create(SignupRequest signupRequest){
        return userDetailsRepository.save(
                new User(
                        signupRequest.getEmail(),
                        passwordEncoder.encode(signupRequest.getPassword()
                        )
                )
        );
    }
}

