package com.hypnos.Hypnos.dtos.Likes;
//Contiene los datos que el usuario envía al servidor como parte de una solicitud.

import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data // Genera automáticamente los métodos getter, setter, equals, hashCode y toString.
@AllArgsConstructor // Crea un constructor sin argumentos.
public class LikeRequestDto {
    private User user;
    private UUID publication;
}
