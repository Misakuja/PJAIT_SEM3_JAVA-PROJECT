package pjatk.edu.pl.pokemon_integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import pjatk.edu.pl.pokemon_integration.service.DatabaseService;

@SpringBootApplication(scanBasePackages = {"pjatk.edu.pl.pokemon_data", "pjatk.edu.pl.pokemon_integration"})
@EnableCaching
public class PokemonIntegrationApplication implements CommandLineRunner {
    private final DatabaseService databaseService;

    @Autowired
    public PokemonIntegrationApplication(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PokemonIntegrationApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        databaseService.fetchAndSaveTypes();
//        databaseService.fetchAndSaveMoves();
//        databaseService.fetchAndSaveAbilities();
        databaseService.fetchAndSavePokemons();
        databaseService.fetchAndSaveItems();
    }
}
