package pjatk.edu.pl.pokemon_integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import pjatk.edu.pl.pokemon_integration.service.PokemonService;

@SpringBootApplication(scanBasePackages = {"pjatk.edu.pl.pokemon_data", "pjatk.edu.pl.pokemon_integration"})
@EnableCaching
public class PokemonIntegrationApplication implements CommandLineRunner {
    private final PokemonService pokemonService;

    @Autowired
    public PokemonIntegrationApplication(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PokemonIntegrationApplication.class, args);
    }

    @Override
    public void run(String... args) {
        pokemonService.fetchAndSavePokemons(5);
    }
}
