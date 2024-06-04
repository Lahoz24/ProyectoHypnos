package com.hypnos.Hypnos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToMany(mappedBy = "likedComments")
    @JsonIgnore
    private List<User> likedByUsers;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference // Usar @JsonBackReference en el lado hijo de la relación
    private User user;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    @JsonManagedReference // Usar @JsonManagedReference en el lado padre de la relación
    private Publication publication;

    @CreatedDate
    private LocalDateTime createdAt;
}
