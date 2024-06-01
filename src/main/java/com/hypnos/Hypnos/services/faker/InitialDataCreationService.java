package com.hypnos.Hypnos.services.faker;

import com.hypnos.Hypnos.models.*;
import com.hypnos.Hypnos.repositories.UserDetailsRepository;
import com.hypnos.Hypnos.services.category.CategoryService;
import com.hypnos.Hypnos.services.comment.CommentService;
import com.hypnos.Hypnos.services.publication.PublicationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InitialDataCreationService {
    private final UserDetailsRepository userDetailsRepository;
    private final CategoryService categoryService;
    private final PublicationService publicationService;
    private final CommentService commentService;
    private final Faker faker = new Faker(new Locale("en-US"));

    @PostConstruct
    public void init() {
        // Verificar y crear usuarios por defecto
        createDefaultAdminUser();
        if (userDetailsRepository.count() == 2) {
            createFakeUser(10);
        }

        // Verificar y crear categorías por defecto
        if (categoryService.findAll().isEmpty()) {
            createDefaultCategories();
        }

        List<User> users = userDetailsRepository.findAll();
        List<Category> categories = categoryService.findAll();

        // Verificar y crear publicaciones ficticias
        if (publicationService.findAll().isEmpty()) {
            createFakePublications(20, users, categories);
        }

        // Verificar y crear comentarios ficticios
        if (commentService.findAll().isEmpty()) {
            createDefaultComment(15, users, publicationService.findAll());
        }
    }

    public void createDefaultAdminUser() {
        String defaultAliasAl = "alvaro_lahoz";
        String defaultAliasAn = "ana_batres";
        if (!userDetailsRepository.existsByAlias(defaultAliasAl) && !userDetailsRepository.existsByAlias(defaultAliasAn) ) {
            User user = User.builder()
                    .firstname("Alvaro")
                    .lastname("Lahoz")
                    .email("alvarolahozmontero@gmail.com")
                    .alias(defaultAliasAl)
                    .password("$2a$12$K4tojeaYWMK55KzWzDWtLOuuUjRTkycWhSGHYWA2LXMZqmZUtuXPL") // Esto es "password" codificado con bcrypt)
                    .role(Role.ADMIN)
                    .build();

            userDetailsRepository.save(user);

            User user2 = User.builder()
                    .firstname("Ana")
                    .lastname("Batres")
                    .email("anabatrescuellar@gmail.com")
                    .alias(defaultAliasAn)
                    .password("$2a$12$K4tojeaYWMK55KzWzDWtLOuuUjRTkycWhSGHYWA2LXMZqmZUtuXPA") // Esto es "password" codificado con bcrypt)
                    .role(Role.ADMIN)
                    .build();

            userDetailsRepository.save(user2);
        }


    }

    public void createFakeUser(int number) {
        if (number <= 0) return;
        for (int i = 0; i < number; i++) {
            User user = User.builder()
                    .firstname(faker.name().firstName())
                    .lastname(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .alias(faker.internet().username())
                    .password("$2a$12$K4tojeaYWMK55KzWzDWtLOuuUjRTkycWhSGHYWA2LXMZqmZUtuXPL") // Esto es "password" codificado con bcrypt
                    .role(Role.USER)
                    .build();

            userDetailsRepository.save(user);
        }
    }

    public void createDefaultCategories() {
        Category pesadilla = Category.builder()
                .name("Pesadilla")
                .description("Sueños que provocan miedo, ansiedad o terror. Estos sueños suelen involucrar situaciones amenazantes, peligrosas o perturbadoras que causan una fuerte reacción emocional al despertar.")
                .build();

        Category erotico = Category.builder()
                .name("Erótico")
                .description("Sueños con contenido sexual explícito o implícito. Pueden involucrar deseos, fantasías y situaciones íntimas con conocidos o desconocidos.")
                .build();

        Category lucido = Category.builder()
                .name("Lúcido")
                .description("Sueños en los que el soñador es consciente de que está soñando y puede ejercer cierto control sobre el desarrollo del sueño. Estos sueños suelen ser muy vívidos y realistas.")
                .build();

        Category recurrente = Category.builder()
                .name("Recurrente")
                .description("Sueños que se repiten con frecuencia, a menudo con el mismo tema, escenario o situación. Pueden reflejar preocupaciones, miedos o problemas no resueltos en la vida del soñador.")
                .build();

        Category profetico = Category.builder()
                .name("Profético")
                .description("Sueños que parecen predecir el futuro o proporcionar información sobre eventos que aún no han ocurrido. Estos sueños a menudo tienen un significado especial para el soñador.")
                .build();

        Category alegre = Category.builder()
                .name("Alegre")
                .description("Sueños que generan sentimientos de felicidad, alegría o euforia. Pueden involucrar situaciones placenteras, reencuentros con seres queridos o experiencias de éxito y realización personal.")
                .build();

        Category fantastico = Category.builder()
                .name("Fantástico")
                .description("Sueños con escenarios y eventos fantásticos que no podrían ocurrir en la vida real. Pueden incluir elementos de magia, criaturas míticas y mundos imaginarios.")
                .build();

        Category vuelos = Category.builder()
                .name("Vuelos")
                .description("Sueños en los que el soñador experimenta la sensación de volar o levitar. Estos sueños a menudo están asociados con sentimientos de libertad y liberación.")
                .build();

        Category caida = Category.builder()
                .name("Caída")
                .description("Sueños en los que el soñador siente que está cayendo desde una gran altura. Estos sueños suelen estar acompañados de una sensación de miedo y pueden provocar un despertar abrupto.")
                .build();

        Category examen = Category.builder()
                .name("Examen")
                .description("Sueños en los que el soñador se encuentra en una situación de examen o prueba. Estos sueños a menudo reflejan ansiedad o estrés relacionado con el rendimiento y las expectativas.")
                .build();

        Category aventura = Category.builder()
                .name("Aventura")
                .description("Sueños que involucran aventuras emocionantes, exploración de lugares desconocidos o participación en misiones desafiantes. Estos sueños pueden estar llenos de acción y emoción.")
                .build();

        Category transformacion = Category.builder()
                .name("Transformación")
                .description("Sueños en los que el soñador o alguien más experimenta una transformación física o emocional significativa. Pueden incluir cambios de forma, identidad o habilidades.")
                .build();

        Category triste = Category.builder()
                .name("Triste")
                .description("Sueños que provocan sentimientos de tristeza, melancolía o dolor emocional. Estos sueños pueden involucrar pérdidas, despedidas, situaciones de soledad o eventos tristes del pasado.")
                .build();

        categoryService.save(pesadilla);
        categoryService.save(erotico);
        categoryService.save(lucido);
        categoryService.save(recurrente);
        categoryService.save(profetico);
        categoryService.save(alegre);
        categoryService.save(fantastico);
        categoryService.save(vuelos);
        categoryService.save(caida);
        categoryService.save(examen);
        categoryService.save(aventura);
        categoryService.save(transformacion);
        categoryService.save(triste);
    }


    public void createFakePublications(int number, List<User> users, List<Category> categories) {
        if (number <= 0) return;
        for (int i = 0; i < number; i++) {
            User randomUser = getRandomElementUser(users);
            List<Category> randomCategories = getRandomCategories(categories);

            Publication publication = Publication.builder()
                    .text(faker.lorem().sentence())
                    .user(randomUser)
                    .categories(randomCategories)
                    .build();

            publicationService.save(publication);
        }
    }

    public void createDefaultComment(int number, List<User> users, List<Publication> publications) {
        if (number <= 0) return;
        for (int i = 0; i < number; i++) {
            User randomUser = getRandomElementUser(users);
            Publication randomPublication = getRandomElementPublication(publications);

            Comment comment = Comment.builder()
                    .text(faker.lorem().sentence())
                    .user(randomUser)
                    .publication(randomPublication)
                    .build();

            commentService.save(comment); // Debes inyectar el servicio de comentarios (commentService)
        }
    }

    private User getRandomElementUser(List<User> list) {
        return list.get(faker.number().numberBetween(0, list.size()));
    }

    private Publication getRandomElementPublication(List<Publication> list) {
        return list.get(faker.number().numberBetween(0, list.size()));
    }

    private List<Category> getRandomCategories(List<Category> categories) {
        int numCategories = faker.number().numberBetween(1, categories.size());
        List<Category> randomCategories = new ArrayList<>();
        Set<Integer> chosenIndexes = new HashSet<>();

        for (int i = 0; i < numCategories; i++) {
            int index = faker.number().numberBetween(0, categories.size());
            while (chosenIndexes.contains(index)) {
                index = faker.number().numberBetween(0, categories.size());
            }
            chosenIndexes.add(index);
            randomCategories.add(categories.get(index));
        }

        return randomCategories;
    }
    public List<Publication> findAllPublications() {
        return publicationService.findAll();
    }
}