package com.hypnos.Hypnos.dtos.Publication;

import com.hypnos.Hypnos.models.user.User;

import com.hypnos.Hypnos.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//Contiene los datos que el servidor envía de vuelta al usuario como resultado de una solicitud.
@Data
@AllArgsConstructor
public class PublicationResponseDto {
    private UUID uuid;
    private String title;
    private String description;
    private Boolean containsImage;
    private Boolean containsAudio;
    private Boolean containsText;
    private LocalDateTime publicationDate;
    private UUID uuidUser;
    private List<Comment> comments; // Lista de comentarios asociados a la publicación
    //  private List<Like> likes; // Lista de "me gusta" asociados a la publicación
}
