package com.hypnos.Hypnos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hypnos.Hypnos.models.user.User;

public interface UserDetailsRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

