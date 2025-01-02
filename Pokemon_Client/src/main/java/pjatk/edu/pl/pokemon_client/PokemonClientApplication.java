package pjatk.edu.pl.pokemon_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pjatk.edu.pl.pokemon_data", "pjatk.edu.pl.pokemon_client", "pjatk.edu.pl.pokemon_api"})
public class PokemonClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(PokemonClientApplication.class, args);
    }

}
