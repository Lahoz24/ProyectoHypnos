package com.hypnos.Hypnos.services.user;

import com.hypnos.Hypnos.auth.SignupRequest;
import com.hypnos.Hypnos.dtos.user.UserResponseDto;
import com.hypnos.Hypnos.mappers.UserMapper;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.Role;
import com.hypnos.Hypnos.models.User;
import com.hypnos.Hypnos.models.UserFollowing;
import com.hypnos.Hypnos.repositories.UserDetailsRepository;
import com.hypnos.Hypnos.repositories.UserFollowingRepository;
import com.hypnos.Hypnos.services.comment.CommentService;
import com.hypnos.Hypnos.services.publication.PublicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final PublicationService publicationService;
    private final CommentService commentService;
    private final UserFollowingRepository userFollowingRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDetailsRepository.findByEmail(email);
    }

    public List<User> searchUsersByAlias(String alias) {
        return userDetailsRepository.findByAliasContainingIgnoreCase(alias);
    }

    public List<User> findAll() {
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


    public List<User> findByAliasContainsIgnoreCase(String alias) {
        return userDetailsRepository.findByAliasContainingIgnoreCase(alias);
    }
    public User findByAlias(String alias) {
        return userDetailsRepository.findByAlias(alias);
    }
    @Transactional
    public void deleteByAlias(String alias) {
        User user = userDetailsRepository.findByAlias(alias);

        if (user != null) {
            // Eliminar comentarios asociados a publicaciones del usuario
            List<Publication> publications = publicationService.findPublicationByUserAlias(alias);
            List<Long> publicationIds = publications.stream()
                    .map(Publication::getId)
                    .collect(Collectors.toList());

            for (Long id : publicationIds) {
                commentService.deleteCommentsByPublicationId(id);
            }

            // Eliminar publicaciones del usuario
            publicationService.deletePublicationsByIds(publicationIds);

            // Eliminar comentarios asociados directamente al usuario
            commentService.deleteCommentsByUserId(user.getId());

            // Eliminar relaciones de seguimiento
            List<UserFollowing> followers = userFollowingRepository.findByFollowed(user);
            List<UserFollowing> following = userFollowingRepository.findByFollower(user);
            userFollowingRepository.deleteAll(followers);
            userFollowingRepository.deleteAll(following);

            // Eliminar el usuario
            userDetailsRepository.delete(user);
        }
    }
    @Transactional
    public void deleteUsersByAliases(List<String> aliases) {
        for (String alias : aliases) {
            deleteByAlias(alias);
        }
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