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
        return getEntityById("/pokemon/id/" + id, id, new ParameterizedTypeReference<Pokemon>() {});
    }
}
