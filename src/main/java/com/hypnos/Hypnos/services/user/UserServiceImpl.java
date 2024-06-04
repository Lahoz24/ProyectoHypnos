package com.hypnos.Hypnos.services.user;

import com.hypnos.Hypnos.auth.SignupRequest;
import com.hypnos.Hypnos.dtos.user.UserResponseDto;
import com.hypnos.Hypnos.mappers.UserMapper;
import com.hypnos.Hypnos.models.Role;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.repositories.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Primary
public class UserServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDetailsRepository.findByEmail(email);
    }

    public List<User> searchUsersByAlias(String alias) {
        return userDetailsRepository.findByAliasContainingIgnoreCase(alias);
    }


    public List<User> getAll() {
        return userDetailsRepository.findAll();
    }

    public User create(SignupRequest signupRequest) {
        String alias = signupRequest.getAlias();
        if (!alias.startsWith("@")) {
            alias = "@" + alias;
        }

        User user = new User();
        user.setFirstname(signupRequest.getFirstname());
        user.setLastname(signupRequest.getLastname());
        user.setAlias(alias);
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(Role.USER);

        return userDetailsRepository.save(user);
    }

    public UserResponseDto followUser(Long userId, Long followId) {
        User user = userDetailsRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        User userToFollow = userDetailsRepository.findById(followId)
                .orElseThrow(() -> new IllegalArgumentException("User to follow not found"));

        if (user.getFollowing().contains(userToFollow)) {
            throw new IllegalArgumentException("Already following this user");
        }

        user.getFollowing().add(userToFollow);
        userToFollow.getFollowers().add(user);
        userDetailsRepository.save(user);
        userDetailsRepository.save(userToFollow);

        // Convert the updated user to UserResponseDto using UserMapper
        return userMapper.toResponse(user);
    }
    public void unfollowUser(Long userId, Long userToUnfollowId) {
        User user = userDetailsRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User userToUnfollow = userDetailsRepository.findById(userToUnfollowId).orElseThrow(() -> new RuntimeException("User to unfollow not found"));

        user.getFollowing().remove(userToUnfollow);
        userDetailsRepository.save(user);
    }


    public List<User> getFollowing(Long userId) {
        User user = userDetailsRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFollowing();
    }

    public List<User> getFollowers(Long userId) {
        User user = userDetailsRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFollowers();
    }

    public List<User> findByFollowing_Id(Long userId) {
        return userDetailsRepository.findByFollowing_Id(userId);
    }

    public List<User> findByFollowers_Id(Long userId) {
        return userDetailsRepository.findByFollowers_Id(userId);
    }

    public Long countPublications(Long userId) {
        return userDetailsRepository.countPublications(userId);
    }

    public User findByAliasContainsIgnoreCase(String alias) {
        return userDetailsRepository.findByAliasContainsIgnoreCase(alias);
    }
    public User findByAlias(String alias) {
        return userDetailsRepository.findByAlias(alias);
    }
    public User findByEmail(String email) {
        return userDetailsRepository.findByAlias(email);
    }
    public void deleteByAlias(String alias) {
        userDetailsRepository.deleteByAlias(alias);
    }
    public User findById(Long id) {
        Optional<User> optionalUser = userDetailsRepository.findById(id);
        return optionalUser.orElse(null);
    }

    private void patchUser(User userUpdated, User user) {
        if (user.getFirstname() != null) {
            userUpdated.setFirstname(user.getFirstname());
        }
        if (user.getLastname() != null) {
            userUpdated.setLastname(user.getLastname());
        }
        if (user.getPassword() != null) {
            userUpdated.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getAlias() != null) {
            userUpdated.setAlias(user.getAlias());
        }
        if (user.getEmail() != null) {
            userUpdated.setEmail(user.getEmail());
        }
    }

    public User patch(String alias, User user) {
        User userUpdated = this.findByAlias(alias);
        if (userUpdated == null) {
            throw new RuntimeException("User not found");
        }

        patchUser(userUpdated, user);

        return userDetailsRepository.save(userUpdated);
    }

}