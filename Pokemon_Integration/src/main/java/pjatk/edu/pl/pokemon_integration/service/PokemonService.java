package pjatk.edu.pl.pokemon_integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pjatk.edu.pl.pokemon_data.dto.PokemonDto;
import pjatk.edu.pl.pokemon_data.dto.PokemonResponse;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.repository.*;

@Service
public class PokemonService {
    private final RestTemplate restTemplate;
    private final PokemonRepository pokemonRepository;
    private final AbilityRepository abilityRepository;
    private final ItemRepository itemRepository;
    private final MoveRepository moveRepository;
    private final TypeRepository typeRepository;

    @Autowired
    public PokemonService(RestTemplate restTemplate, PokemonRepository pokemonRepository, AbilityRepository abilityRepository, ItemRepository itemRepository, MoveRepository moveRepository, TypeRepository typeRepository) {
        this.restTemplate = restTemplate;
        this.pokemonRepository = pokemonRepository;
        this.abilityRepository = abilityRepository;
        this.itemRepository = itemRepository;
        this.moveRepository = moveRepository;
        this.typeRepository = typeRepository;
    }

    public void fetchAndSavePokemons() {
        String url = "https://pokeapi.co/api/v2/pokemon?limit=100";
        PokemonResponse response = restTemplate.getForObject(url, PokemonResponse.class);

        if (response != null && response.getResults() != null) {
            for (PokemonDto dto : response.getResults()) {
                savePokemon(dto);
            }
        }
    }

    private void savePokemon(PokemonDto dto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setApiId(dto.apiId());
        pokemon.setName(dto.name());
        pokemon.setHeight(dto.height());
        pokemon.setWeight(dto.weight());
        pokemon.setBaseExperience(dto.baseExperience());
        pokemonRepository.save(pokemon);
    }
}

