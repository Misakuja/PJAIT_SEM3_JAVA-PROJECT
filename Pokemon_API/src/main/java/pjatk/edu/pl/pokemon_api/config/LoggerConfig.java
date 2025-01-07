package pjatk.edu.pl.pokemon_api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pjatk.edu.pl.pokemon_api.service.*;

@Configuration
public class LoggerConfig {

    @Bean
    @Qualifier("baseServiceLogger")
    public Logger baseServiceLogger() {
        return LoggerFactory.getLogger(BaseService.class);
    }

    @Bean
    @Qualifier("abilityServiceLogger")
    public Logger abilityServiceLogger() {
        return LoggerFactory.getLogger(AbilityService.class);
    }

    @Bean
    @Qualifier("itemServiceLogger")
    public Logger itemServiceLogger() {
        return LoggerFactory.getLogger(ItemService.class);
    }

    @Bean
    @Qualifier("moveServiceLogger")
    public Logger moveServiceLogger() {
        return LoggerFactory.getLogger(MoveService.class);
    }

    @Bean
    @Qualifier("pokemonServiceLogger")
    public Logger pokemonServiceLogger() {
        return LoggerFactory.getLogger(PokemonService.class);
    }

    @Bean
    @Qualifier("typeServiceLogger")
    public Logger typeServiceLogger() {
        return LoggerFactory.getLogger(TypeService.class);
    }
}