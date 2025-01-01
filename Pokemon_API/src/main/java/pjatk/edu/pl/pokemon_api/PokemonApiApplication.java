package pjatk.edu.pl.pokemon_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pjatk.edu.pl.pokemon_data", "pjatk.edu.pl.pokemon_api"})
public class PokemonApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PokemonApiApplication.class, args);
	}

}
