package com.hypnos.Hypnos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hypnos.Hypnos.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@Repository
@CrossOrigin(origins = "http://127.0.0.1:5173")
public interface UserDetailsRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByAlias(String alias);
    Boolean existsByAlias(String alias);
    List<User> findByAliasContainingIgnoreCase(String alias);

    User findByAliasContainsIgnoreCase(String alias);
    List<User> findByFollowing_Id(Long userId);
    List<User> findByFollowers_Id(Long userId);
    @Query("SELECT COUNT(u) FROM User u JOIN u.following f WHERE f.id = :userId")
    Long countFollowing(@Param("userId") Long userId);

    @Query("SELECT COUNT(u) FROM User u JOIN u.followers f WHERE f.id = :userId")
    Long countFollowers(@Param("userId") Long userId);

    @Query("SELECT COUNT(p) FROM Publication p WHERE p.user.id = :userId")
    Long countPublications(@Param("userId") Long userId);

    void deleteByAlias(String alias);

}
