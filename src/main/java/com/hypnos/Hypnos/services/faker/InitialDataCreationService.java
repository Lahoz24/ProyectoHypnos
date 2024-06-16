package com.hypnos.Hypnos.services.faker;

import com.hypnos.Hypnos.models.*;
import com.hypnos.Hypnos.repositories.LikedCommentRepository;
import com.hypnos.Hypnos.repositories.LikedPublicationRepository;
import com.hypnos.Hypnos.repositories.UserDetailsRepository;
import com.hypnos.Hypnos.services.category.CategoryService;
import com.hypnos.Hypnos.services.comment.CommentService;
import com.hypnos.Hypnos.services.publication.PublicationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InitialDataCreationService {
    private final UserDetailsRepository userDetailsRepository;
    private final CategoryService categoryService;
    private final PublicationService publicationService;
    private final CommentService commentService;
    private final LikedCommentRepository likedCommentRepository;
    private final LikedPublicationRepository likedPublicationRepository;

    private final Faker faker = new Faker(new Locale("es-ES")); // Cambio a español

    private final List<String> publicationTitles = Arrays.asList(
            "Fui consciente de que estaba soñando cuando miré mis manos",
            "La pesadilla que me dejó sin aliento",
            "Un viaje a través del tiempo en mis sueños",
            "El sueño más alegre que he tenido",
            "No dejo de soñar con la vecina del 4º",
            "Sueño profético: adiós al estreñimiento",
            "Explorando mundos fantásticos en mis sueños",
            "La caída interminable: mi sueño recurrente",
            "Volando sobre la ciudad: un sueño inolvidable",
            "Un examen sorpresa en mis sueños",
            "Una aventura nocturna inolvidable",
            "Me transformé en una cobaya",
            "Un reencuentro con seres queridos",
            "La tristeza que me acompaña",
            "El mensaje oculto",
            "Una noche en el bosque encantado",
            "Perdido en un laberinto interminable",
            "Un paseo por la playa al atardecer",
            "Conversaciones con extraños en mis sueños",
            "Visitando una ciudad desconocida"
    );

    private final List<String> publicationTexts = Arrays.asList(
            "Hace unas noches tuve un sueño lúcido increíble. Pude controlar todo lo que ocurría y fue una experiencia liberadora.",
            "Anoche tuve una pesadilla en la que me perseguían. Me desperté con el corazón latiendo muy rápido.",
            "Soñé que viajaba en el tiempo. Fue tan real que al despertar estaba confundido sobre en qué año estaba.",
            "Tuve un sueño muy alegre en el que me reencontraba con viejos amigos. Me desperté sintiéndome muy feliz.",
            "He tenido el mismo sueño muchas veces este mes. Creo que mi subconsciente está tratando de decirme algo.",
            "Soñé que cagaba en mi sueño y se hizo realidad. Fue una experiencia muy extraña, ya que me desperté cagado.",
            "Anoche, mi sueño me llevó a un mundo de fantasía lleno de criaturas mágicas y paisajes increíbles.",
            "He tenido este sueño de caer interminablemente desde una gran altura muchas veces. Siempre me despierto asustado.",
            "Soñé que volaba sobre la ciudad. La sensación de libertad era indescriptible.",
            "Estaba en medio de un examen sorpresa y no sabía ninguna respuesta. Me desperté muy nervioso.",
            "En mi sueño, estaba en una emocionante aventura en un lugar desconocido. Fue realmente increíble.",
            "Soñé que me transformaba en una cobaya. Fue una experiencia muy intrigante.",
            "Soñé que me reencontraba con un ser querido que ya no está conmigo. Fue un sueño muy emotivo.",
            "Tuve un sueño muy triste en el que revivía una pérdida importante. Me desperté sintiéndome muy melancólico.",
            "Mis sueños a veces parecen tener mensajes ocultos. Anoche, tuve uno que me dejó pensando todo el día.",
            "Me encontré en un bosque encantado, lleno de luces y sonidos mágicos. La experiencia fue tan vívida que no quería despertar.",
            "Estaba atrapado en un laberinto sin salida. La sensación de desesperación era abrumadora, pero finalmente encontré una salida.",
            "Soñé que caminaba por una playa al atardecer. El sonido de las olas y el cielo anaranjado eran simplemente perfectos.",
            "En mis sueños, a menudo hablo con personas que no conozco en la vida real. Es fascinante cómo nuestras mentes crean estos personajes.",
            "Visité una ciudad que nunca había visto antes. Cada rincón tenía algo nuevo y sorprendente."
    );

    private final List<String> commentTexts = Arrays.asList(
            "¡Qué interesante! Nunca he tenido un sueño lúcido, pero me encantaría experimentarlo.",
            "Esa pesadilla suena aterradora. A mí también me persiguieron una vez en un sueño.",
            "Tu sueño sobre viajar en el tiempo me recuerda a una película que vi hace poco.",
            "Qué bonito que hayas tenido un sueño tan feliz. Los sueños pueden afectar mucho nuestro estado de ánimo.",
            "Los sueños recurrentes a veces significan que hay algo en tu vida que necesitas resolver.",
            "¡Vaya! Yo también he tenido sueños proféticos. Es una experiencia increíble.",
            "Sueños con mundos fantásticos son mis favoritos. Siempre despierto con una sensación de asombro.",
            "Las caídas en sueños son realmente aterradoras. Me despierto con el corazón a mil.",
            "Volar en sueños es una de las mejores sensaciones que he experimentado.",
            "Sueños con exámenes son tan estresantes. Siempre me despierto aliviado de que no era real.",
            "Las aventuras en sueños pueden ser tan emocionantes como en la vida real.",
            "Transformarse en sueños es algo que nunca me ha pasado. Debe ser muy interesante.",
            "Reencontrarse con seres queridos en sueños puede ser muy reconfortante.",
            "Los sueños tristes pueden afectar nuestro estado de ánimo todo el día. Espero que estés bien.",
            "A veces también siento que mis sueños tienen mensajes para mí. Es algo fascinante.",
            "Me encantaría tener un sueño lúcido. He leído mucho sobre cómo inducirlos.",
            "Tu pesadilla me recordó a un sueño que tuve hace años. Es sorprendente cómo pueden afectarnos.",
            "Viajar en el tiempo en sueños suena tan emocionante. Me pregunto qué significa.",
            "Es maravilloso despertar con una sensación de felicidad gracias a un buen sueño.",
            "Soñar con la misma persona repetidamente debe ser una señal de algo. ¿Lo has investigado?",
            "Los sueños proféticos son algo que siempre me ha intrigado. ¿Alguna vez has intentado interpretarlos?",
            "Explorar mundos de fantasía en sueños es una experiencia increíble. Siempre me despierto queriendo volver.",
            "Caer en sueños siempre me hace despertar con el corazón latiendo rápido. Es una sensación muy intensa.",
            "La sensación de volar en sueños es inigualable. Siempre me siento libre y en paz.",
            "Soñar con exámenes me estresa mucho, incluso si no tengo ninguno en la vida real.",
            "Las aventuras en sueños pueden ser tan realistas que me despierto queriendo más.",
            "Transformarse en sueños suena como una experiencia muy interesante. Me pregunto qué significa.",
            "Reencontrarse con seres queridos en sueños puede ser muy reconfortante. Es como tener una segunda oportunidad.",
            "Los sueños tristes me dejan melancólico todo el día. Espero que estés bien.",
            "Mis sueños también tienen mensajes ocultos a veces. Me gusta intentar descifrarlos."
    );


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

        // Filtrar los usuarios no admin
        List<User> nonAdminUsers = filterNonAdminUsers(users);

        // Verificar y crear publicaciones ficticias
        if (publicationService.findAll().isEmpty()) {
            createFakePublications(20, nonAdminUsers, categories);
        }

        // Verificar y crear comentarios ficticios
        if (commentService.findAll().isEmpty()) {
            createFakeComments(50, nonAdminUsers, publicationService.findAll());
        }

        // Verificar y crear me gustas ficticios
        createFakeLikes(50, nonAdminUsers, publicationService.findAll(), commentService.findAll());
    }

    private List<User> filterNonAdminUsers(List<User> users) {
        List<User> nonAdminUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getRole() != Role.ADMIN) {
                nonAdminUsers.add(user);
            }
        }
        return nonAdminUsers;
    }

    public void createDefaultAdminUser() {
        String defaultAliasAl = "alvaro_lahoz";
        String defaultAliasAn = "ana_batres";
        String defaultPassword = "password123";

        if (!userDetailsRepository.existsByAlias(defaultAliasAl) && !userDetailsRepository.existsByAlias(defaultAliasAn)) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(defaultPassword);

            User user1 = User.builder()
                    .firstname("Alvaro")
                    .lastname("Lahoz")
                    .email("alvarolahozmontero@gmail.com")
                    .alias(defaultAliasAl)
                    .password(encodedPassword)
                    .role(Role.ADMIN)
                    .build();

            userDetailsRepository.save(user1);

            User user2 = User.builder()
                    .firstname("Ana")
                    .lastname("Batres")
                    .email("anabatrescuellar@gmail.com")
                    .alias(defaultAliasAn)
                    .password(encodedPassword)
                    .role(Role.ADMIN)
                    .build();

            userDetailsRepository.save(user2);
        }
    }

    public void createFakeUser(int number) {
        String defaultPassword = "password123";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(defaultPassword);
        if (number <= 0) return;
        for (int i = 0; i < number; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            // Limpio nombre y apellido de espacios y caracteres especiales
            String cleanedFirstName = cleanSpecialCharacters(firstName).toLowerCase();
            String cleanedLastName = cleanSpecialCharacters(lastName).toLowerCase();

            // Creo correo electrónico
            String email = faker.internet().emailAddress(cleanedFirstName + "." + cleanedLastName);

            // Creo alias
            String alias = "@" + cleanedFirstName + cleanedLastName;

            User user = User.builder()
                    .firstname(firstName)
                    .lastname(lastName)
                    .email(email)
                    .alias(alias)
                    .password(encodedPassword)
                    .role(Role.USER)
                    .build();

            userDetailsRepository.save(user);
        }
    }

    private String cleanSpecialCharacters(String input) {
        // Elimino caracteres especiales y espacios
        return input.replaceAll("[^a-zA-Z0-9]", "");
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
            User randomUser = getRandomElement(users);
            List<Category> randomCategories = getRandomCategories(categories);

            int index = i % publicationTitles.size();

            Publication publication = Publication.builder()
                    .title(publicationTitles.get(index))
                    .text(publicationTexts.get(index))
                    .user(randomUser)
                    .categories(randomCategories)
                    .createdAt(LocalDateTime.now())
                    .build();

            publicationService.save(publication);
        }
    }


    public void createFakeComments(int number, List<User> users, List<Publication> publications) {
        if (number <= 0) return;
        for (int i = 0; i < number; i++) {
            User randomUser = getRandomElement(users);
            Publication randomPublication = getRandomElement(publications);

            Comment comment = Comment.builder()
                    .text(getRandomElement(commentTexts))
                    .user(randomUser)
                    .publication(randomPublication)
                    .createdAt(LocalDateTime.now())
                    .build();

            commentService.save(comment);
        }
    }

    public void createFakeLikes(int number, List<User> users, List<Publication> publications, List<Comment> comments) {
        if (number <= 0) return;
        for (int i = 0; i < number; i++) {
            User randomUser = getRandomElement(users);

            if (faker.bool().bool()) {
                Publication randomPublication = getRandomElement(publications);
                LikePublication like = LikePublication.builder()
                        .user(randomUser)
                        .publication(randomPublication)
                        .build();

                likedPublicationRepository.save(like);
            } else {
                Comment randomComment = getRandomElement(comments);
                LikeComment like = LikeComment.builder()
                        .user(randomUser)
                        .comment(randomComment)
                        .build();

                likedCommentRepository.save(like);
            }
        }
    }

    private <T> T getRandomElement(List<T> list) {
        return list.get(faker.number().numberBetween(0, list.size()));
    }

    private List<Category> getRandomCategories(List<Category> categories) {
        int numCategories = faker.number().numberBetween(1, 4);
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
}
