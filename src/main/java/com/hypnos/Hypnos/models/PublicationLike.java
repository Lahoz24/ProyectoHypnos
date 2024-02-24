package com.hypnos.Hypnos.models;

import com.hypnos.Hypnos.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity // Indica que esta clase es una entidad JPA y está mapeada a una tabla en la base de datos.
@Data // Genera automáticamente los métodos getter, setter, equals, hashCode y toString.
@NoArgsConstructor // Crea un constructor sin argumentos.
@AllArgsConstructor // Genera un constructor que acepta todos los campos de la clase como argumentos
public class PublicationLike {
    @Id
    @Column(name = "id")
    private UUID uuid;

    private UUID publication;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
