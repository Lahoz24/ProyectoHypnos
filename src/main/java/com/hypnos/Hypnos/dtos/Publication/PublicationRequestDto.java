package com.hypnos.Hypnos.dtos.Publication;
//Contiene los datos que el usuario envía al servidor como parte de una solicitud.
import com.hypnos.Hypnos.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data // Genera automáticamente los métodos getter, setter, equals, hashCode y toString.
@AllArgsConstructor // Crea un constructor sin argumentos.
public class PublicationRequestDto {
    private String title;
    private String description;
    private Boolean containsImage;
    private Boolean containsAudio;
    private Boolean containsText;
    private LocalDateTime publicationDate;
    private User user;
}
