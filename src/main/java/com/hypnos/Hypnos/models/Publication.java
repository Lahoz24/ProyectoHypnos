package com.hypnos.Hypnos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hypnos.Hypnos.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity // Indica que esta clase es una entidad JPA y está mapeada a una tabla en la base de datos.
@Data // Genera automáticamente los métodos getter, setter, equals, hashCode y toString.
@NoArgsConstructor // Crea un constructor sin argumentos.
@AllArgsConstructor // Genera un constructor que acepta todos los campos de la clase como argumentos

public class Publication {

    @Id // marcar un atributo como la clave primaria (En este caso el uuid)
    @Column(name = "id") // Nombre de la columna en la base de datos
    private UUID uuid;

    private String title;
    private String description;
    private Boolean containsImage;
    private Boolean containsAudio;
    private Boolean containsText;
    private LocalDateTime publicationDate;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;

}
