package com.hypnos.Hypnos.dtos.publication;

import com.hypnos.Hypnos.models.Category;
import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.User;
import lombok.*;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(force = true) // Para que no me de error en el constructor de jackson
@RequiredArgsConstructor // Para que me cree un constructor con los atributos finales
@AllArgsConstructor
public class PublicationResponseDto {
    private final Long id;
    private String title;
    private String text;
    private final User user;
    private List<Category> categories;
    private List<Comment> comments;
    private List<User> likedByUsers;
    private final LocalDateTime createdAt;
}

