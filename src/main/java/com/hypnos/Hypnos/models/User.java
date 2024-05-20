package com.hypnos.Hypnos.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Getter
    @Column(unique = true, nullable = false)
    private String alias;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Publication> publications;
    @ManyToMany
    private List<Publication> likedPublications;
    @ManyToMany
    private List<Comment> likedComments;

    // Relación de seguidores y seguidos
    @ManyToMany
    @JoinTable(
            name = "user_following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    private List<User> following; // Lista de usuarios a los que sigue este usuario

    @ManyToMany(mappedBy = "following")
    private List<User> followers; // Lista de usuarios que siguen a este usuario
    @CreatedDate
    private LocalDateTime createdAt;



    public User(String firstname,String lastname,String email,String alias, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.alias = alias;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

