package com.hypnos.Hypnos.models;

import com.hypnos.Hypnos.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity // Indica que esta clase es una entidad JPA y está mapeada a una tabla en la base de datos.
@Data // Genera automáticamente los métodos getter, setter, equals, hashCode y toString.
@NoArgsConstructor // Crea un constructor sin argumentos.
@AllArgsConstructor // Genera un constructor que acepta todos los campos de la clase como argumentos

public class Comment {
    @Id // marcar un atributo como la clave primaria (En este caso el uuid)
    @Column(name = "id") // Nombre de la columna en la base de datos
    private UUID uuid;

    private String text;
//  private UUID parentCommentId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Publication publication;

    @ManyToOne
    private Comment parentComment;
    //establece la relación con el comentario padre

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL) //indica que la relación ya está mapeada en la entidad especificada
    // evita la creación de tablas de unión en la base de datos.
    private List<Comment> childComments;
    //representa los comentarios secundarios

//    Cada comentario puede tener una lista de comentarios secundarios y una
//    referencia al comentario padre al que está asociado
//    Si no tiene un comentario padre, ese campo será nulo.

}
