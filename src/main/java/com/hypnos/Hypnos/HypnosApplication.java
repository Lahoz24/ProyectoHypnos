package com.hypnos.Hypnos;

import com.hypnos.Hypnos.services.faker.InitialDataCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class HypnosApplication {

/*	@Autowired
	private InitialDataCreationService initialDataCreationService;*/

	public static void main(String[] args) {
		System.out.println("LOADING...");
		SpringApplication.run(HypnosApplication.class, args);
		System.out.println("                                 ");
		System.out.println("                             (``'``''´´'``'´')");
		System.out.println("                           (                  )");
		System.out.println("                          (   PROYECTO HYPNOS  )");
		System.out.println("                           (                  )");
		System.out.println("                             (.,.,.,.,,..,,.)");
		System.out.println("                          ()");
		System.out.println("                      ()");
		System.out.println("           .-\"''-.  _");
		System.out.println("         .'       `( \\");
		System.out.println("       @/            ')   ,--,__,-\"");
		System.out.println("       /        /      \\ /     /   _/");
		System.out.println("     __|           ,   |/         /");
		System.out.println("   .~  `\\   / \\ ,  |   /");
		System.out.println(" .~      `\\    `  /  _/   _/");
		System.out.println(".~         `\\  ~~`__/    /");
		System.out.println(" ~           `--'/");
		System.out.println("            /   /    /");
		System.out.println("           /  /'    /");
		System.out.println("                     ");

	}

/*	@Bean
	public CommandLineRunner init() {
		return args -> {
			initialDataCreationService.init();
		};
	}*/


}
