package com.hypnos.Hypnos.dtos.Comments;

import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

//Contiene los datos que el servidor envía de vuelta al usuario como resultado de una solicitud.
@Data
@AllArgsConstructor
public class CommentResponseDto {
    private UUID uuid;
    private String text;
    private User user;
    private Publication publication;
    private List<CommentResponseDto> childComments; // Lista de comentarios secundarios
    //  private List<Like> likes; // Lista de "me gusta" asociados a la publicación
}
