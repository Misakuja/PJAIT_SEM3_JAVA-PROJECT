package pjatk.edu.pl.pokemon_client.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;

import java.util.List;

@Service
public class ViewPokemonService extends ViewBaseService {
    public ViewPokemonService(RestClient restClient) {
        super(restClient);
    }

    public List<Pokemon> getAllPokemons() {
        return getAllEntities("/pokemon");
    }

    public void deletePokemon(Long id) {
        deleteEntity("/pokemon/" + id, id);
    }

    public void addPokemon(Pokemon pokemon) {
        addEntity("/pokemon/add", pokemon);
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
