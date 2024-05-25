package com.hypnos.Hypnos.services.user;

import com.hypnos.Hypnos.auth.SignupRequest;
import com.hypnos.Hypnos.dtos.user.UserResponseDto;
import com.hypnos.Hypnos.mappers.UserMapper;
import com.hypnos.Hypnos.models.Role;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con correo electrónico: " + email);
        }

        // Obtener la contraseña codificada almacenada en la base de datos
        String storedPasswordEncoded = user.getPassword();

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail()) // Utiliza el correo electrónico como nombre de usuario
                .password(storedPasswordEncoded) // Utiliza la contraseña codificada almacenada
                .roles(user.getRole().toString())
                .build();
    }



    public List<User> getAll() {
        return userRepository.findAll();
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

        return userRepository.save(user);
    }

    public void followUser(Long userId, Long userToFollowId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User userToFollow = userRepository.findById(userToFollowId).orElseThrow(() -> new RuntimeException("User to follow not found"));

        user.getFollowing().add(userToFollow);
        userRepository.save(user);
    }

    public void unfollowUser(Long userId, Long userToUnfollowId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User userToUnfollow = userRepository.findById(userToUnfollowId).orElseThrow(() -> new RuntimeException("User to unfollow not found"));

        user.getFollowing().remove(userToUnfollow);
        userRepository.save(user);
    }

    public UserResponseDto getUserFollowStats(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toResponse(user);
    }

    public List<User> getFollowing(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFollowing();
    }

    public List<User> getFollowers(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFollowers();
    }

    public List<User> findByFollowing_Id(Long userId) {
        return userRepository.findByFollowing_Id(userId);
    }

    public List<User> findByFollowers_Id(Long userId) {
        return userRepository.findByFollowers_Id(userId);
    }

    public Long countPublications(Long userId) {
        return userRepository.countPublications(userId);
    }

    public User findByAliasContainsIgnoreCase(String alias) {
        return userRepository.findByAliasContainsIgnoreCase(alias);
    }
    public User findByAlias(String alias) {
        return userRepository.findByAlias(alias);
    }
    public void deleteByAlias(String alias) {
        userRepository.deleteByAlias(alias);
    }
}
