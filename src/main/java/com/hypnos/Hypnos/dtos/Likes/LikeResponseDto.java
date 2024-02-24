package com.hypnos.Hypnos.dtos.Likes;

import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.user.User;

import java.util.List;
import java.util.UUID;

//Contiene los datos que el servidor envía de vuelta al usuario como resultado de una solicitud.
public class LikeResponseDto {
    private UUID uuid;
    private User uuidUser;
    private UUID uuidPublication;
}
