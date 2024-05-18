package com.hypnos.Hypnos.repositories;

import com.hypnos.Hypnos.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hypnos.Hypnos.models.User;

import java.util.List;

public interface UserDetailsRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByAliasContainsIgnoreCase(String alias);

}

