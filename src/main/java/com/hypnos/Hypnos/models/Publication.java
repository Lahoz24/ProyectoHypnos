package com.hypnos.Hypnos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String text;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference // Usar @JsonBackReference en el lado hijo de la relación
    private User user;

    @ManyToMany
    @JoinTable(
            name = "publication_category",
            joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnore
    private List<Category> categories;

    @OneToMany(mappedBy = "publication")
    @JsonManagedReference // Uso @JsonManagedReference en el lado padre de la relación
    private List<Comment> comments;

    @JsonIgnore
    @ManyToMany(mappedBy = "likedPublications")
    private List<User> likedByUsers;

    @CreatedDate
    private LocalDateTime createdAt;
}


