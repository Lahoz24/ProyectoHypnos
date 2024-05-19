package com.hypnos.Hypnos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hypnos.Hypnos.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByAliasContainsIgnoreCase(String alias);
    List<User> findByFollowing_Id(Long userId);
    List<User> findByFollowers_Id(Long userId);
    @Query("SELECT COUNT(u) FROM User u JOIN u.following f WHERE f.id = :userId")
    Long countFollowing(@Param("userId") Long userId);

    @Query("SELECT COUNT(u) FROM User u JOIN u.followers f WHERE f.id = :userId")
    Long countFollowers(@Param("userId") Long userId);

    @Query("SELECT COUNT(p) FROM Publication p WHERE p.user.id = :userId")
    Long countPublications(@Param("userId") Long userId);

}

