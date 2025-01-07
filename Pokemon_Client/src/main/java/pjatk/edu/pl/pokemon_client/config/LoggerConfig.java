package pjatk.edu.pl.pokemon_client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pjatk.edu.pl.pokemon_client.service.*;

@Configuration
public class LoggerConfig {

    @Bean
    @Qualifier("viewBaseServiceLogger")
    public Logger viewBaseServiceLogger() {
        return LoggerFactory.getLogger(ViewBaseService.class);
    }

    @Bean
    @Qualifier("viewAbilityServiceLogger")
    public Logger viewAbilityServiceLogger() {
        return LoggerFactory.getLogger(ViewAbilityService.class);
    }

    @Bean
    @Qualifier("viewItemServiceLogger")
    public Logger viewItemServiceLogger() {
        return LoggerFactory.getLogger(ViewItemService.class);
    }

    @Bean
    @Qualifier("viewMoveServiceLogger")
    public Logger viewMoveServiceLogger() {
        return LoggerFactory.getLogger(ViewMoveService.class);
    }

    @Bean
    @Qualifier("viewPokemonServiceLogger")
    public Logger viewPokemonServiceLogger() {
        return LoggerFactory.getLogger(ViewPokemonService.class);
    }

    @Bean
    @Qualifier("viewTypeServiceLogger")
    public Logger viewTypeServiceLogger() {
        return LoggerFactory.getLogger(ViewTypeService.class);
    }
}