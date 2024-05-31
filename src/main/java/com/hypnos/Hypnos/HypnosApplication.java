package com.hypnos.Hypnos;

//import com.hypnos.Hypnos.services.faker.InitialDataCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class HypnosApplication {

//	@Autowired
//	private InitialDataCreationService initialDataCreationService;

	public static void main(String[] args) {
		System.out.println("LOADING...");
		SpringApplication.run(HypnosApplication.class, args);
		System.out.println("*******************************************************************************************************************************************************************************************");
		System.out.println("*                                                                                                                                                      			                          *");
		System.out.println("*                                                                                                                                                      			       			          *");
		System.out.println("*                                                                                                                                                                           		      *");
		System.out.println("*                                                                                   ¡BIENVENIDO                                                                            		          *");
		System.out.println("*                                                                                    A HYPNOS!                                                                          		          *");
		System.out.println("*                                                                                                                                                                           		      *");
		System.out.println("*                                                                                                                                                      			            		      *");
		System.out.println("*                                                                                                                                                                          			      *");
		System.out.println("*******************************************************************************************************************************************************************************************");

		System.out.println("Abre Postman en http://localhost:8080/api/users para ver los usuarios.");
		System.out.println("Abre Postman en http://localhost:8080/api/categories para ver las categorías.");
		System.out.println("Abre Postman en http://localhost:8080/api/publications para ver las publicaciones.");
		System.out.println("Abre Postman en http://localhost:8080/api/comments para ver los comentarios.");
	}

//	@Bean
//	public CommandLineRunner init() {
//		return args -> {
//			initialDataCreationService.init();
//		};
//	}
}
