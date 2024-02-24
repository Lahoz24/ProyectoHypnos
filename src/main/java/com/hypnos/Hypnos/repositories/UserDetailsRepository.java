package com.hypnos.Hypnos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hypnos.Hypnos.models.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

