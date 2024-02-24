package com.hypnos.Hypnos.services;

import com.hypnos.Hypnos.models.Comment;
import com.hypnos.Hypnos.models.Publication;
import com.hypnos.Hypnos.models.user.User;
import com.hypnos.Hypnos.services.Publication.PublicationServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import net.datafaker.Faker;

@Service
public class FakerDataService {

    private final UserDetailsServiceImpl userDetailsService;
    private final PublicationServiceImpl publicationService;

    public FakerDataService(UserDetailsServiceImpl userDetailsService, PublicationServiceImpl publicationService) {
        this.userDetailsService = userDetailsService;
        this.publicationService = publicationService;
    }

    Faker faker = new Faker(new Locale("es"));

    public void insertPublications(PublicationServiceImpl publicationService, int number) {
        if(number <= 0) return;
        List<Publication> publications = publicationService.getAllPublications();

        // Crear el usuario predeterminado una vez
        User defaultAdminUser = createDefaultAdminUser();

        for (int i = 0; i < number; i++) {
            Publication publication= new Publication(
                    UUID.randomUUID(),
                    faker.lorem().sentence(),
                    faker.lorem().paragraph(),
                    faker.bool().bool(),
                    faker.bool().bool(),
                    faker.bool().bool(),
                    LocalDate.now(),
                    defaultAdminUser,
                    generateComments()
            );
            publicationService.addPublication(publication);
        }
    }

    public User createDefaultAdminUser() {
        String name = faker.name().name();
        String lastname = faker.name().name();
        String email = "user_" + UUID.randomUUID() + "@example.com";
        User user = new User(email, "$2a$12$K4tojeaYWMK55KzWzDWtLOuuUjRTkycWhSGHYWA2LXMZqmZUtuXPO"); // Esto es "password" codificado con bcrypt)
        user.setFirstname(name);
        user.setLastname(lastname);
        return userDetailsService.save(user);

    }

    public List<Comment> generateComments() {
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Comment comment = new Comment();
            comment.setUuid(UUID.randomUUID());
            comment.setText(faker.lorem().sentence());
            comment.setParentComment(null);
            comment.setChildComments(null); // Inicializar una lista vacía de comentarios secundarios
            comments.add(comment);
        }
        return comments;
    }

    @PostConstruct
    public void insertInitialPublications() {
        insertPublications(publicationService, 10); // Inserta 10 publicaciones al iniciar la aplicación
    }
}