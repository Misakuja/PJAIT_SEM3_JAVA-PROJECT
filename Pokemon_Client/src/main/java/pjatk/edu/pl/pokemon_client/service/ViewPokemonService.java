package pjatk.edu.pl.pokemon_client.service;

import org.slf4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pjatk.edu.pl.pokemon_data.entity.Ability;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.entity.Type;

import java.util.List;

@Service
public class ViewPokemonService extends ViewBaseService {
    public ViewPokemonService(RestClient restClient, Logger viewPokemonServiceLogger) {
        super(restClient, viewPokemonServiceLogger);
    }

    public List<Pokemon> getAllPokemons() {
        return getAllEntities("/pokemon");
    }

    public List<Ability> getAllAbilitiesByPokemonId(Long id) {
        String logContext = "Fetching abilities for Pokémon ID: " + id;
        return getAllEntitiesOfRelationById("/pokemon/get/ability/" + id, new ParameterizedTypeReference<>() {}, logContext);
    }

    public List<Move> getAllMovesByPokemonId(Long id) {
        String logContext = "Fetching moves for Pokémon ID: " + id;
        return getAllEntitiesOfRelationById("/pokemon/get/move/" + id, new ParameterizedTypeReference<>() {}, logContext);
    }

    public List<Type> getAllTypesByPokemonId(Long id) {
        String logContext = "Fetching types for Pokémon ID: " + id;
        return getAllEntitiesOfRelationById("/pokemon/get/type/" + id, new ParameterizedTypeReference<>() {}, logContext);
    }

    public void deletePokemon(Long id) {
        deleteEntity("/pokemon/" + id, id);
    }

    public void addPokemon(Pokemon pokemon) {
        addEntity("/pokemon", pokemon);
    }

    public void updatePokemon(Pokemon pokemon, Long id) {
        updateEntity("/pokemon/" + id, pokemon, id);
    }

    public Pokemon getPokemonById(Long id) {
        return getEntityByField("/pokemon/apiId/" + id, id, new ParameterizedTypeReference<>() {});
    }

    public Pokemon getPokemonByApiId(Integer apiId) {
        return getEntityByField("/pokemon/apiId/" + apiId, apiId, new ParameterizedTypeReference<>() {});
    }

    public Pokemon getPokemonByName(String name) {
        return getEntityByField("/pokemon/name/" + name, name, new ParameterizedTypeReference<>() {});
    }

    public List<Pokemon> getPokemonByBaseExperience(Integer baseExperience) {
        return getEntityListByField("/pokemon/baseExperience/" + baseExperience, baseExperience);
    }

    public List<Pokemon> getPokemonByHeight(Integer height) {
        return getEntityListByField("/pokemon/height/" + height, height);
    }

    public List<Pokemon> getPokemonByWeight(Integer weight) {
        return getEntityListByField("/pokemon/weight/" + weight, weight);
    }

}
