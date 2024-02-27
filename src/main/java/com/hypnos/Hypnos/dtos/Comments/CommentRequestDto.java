package com.hypnos.Hypnos.dtos.Comments;
//Contiene los datos que el usuario envía al servidor como parte de una solicitud.

import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data // Genera automáticamente los métodos getter, setter, equals, hashCode y toString.
@AllArgsConstructor // Crea un constructor sin argumentos.
public class CommentRequestDto {
    private String text;
    private UUID userUUID;
    private Publication publication;
}
