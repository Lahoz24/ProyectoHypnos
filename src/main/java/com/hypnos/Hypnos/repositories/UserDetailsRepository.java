package com.hypnos.Hypnos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hypnos.Hypnos.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5173")
public interface UserDetailsRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByAlias(String alias);
    Boolean existsByAlias(String alias);
    List<User> findByAliasContainingIgnoreCase(String alias);
    void deleteByAlias(String alias);

}
