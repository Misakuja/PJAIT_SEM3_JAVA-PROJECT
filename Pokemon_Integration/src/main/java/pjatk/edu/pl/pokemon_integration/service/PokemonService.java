package pjatk.edu.pl.pokemon_integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pjatk.edu.pl.pokemon_data.dto.PokemonDto;
import pjatk.edu.pl.pokemon_data.dto.TypeDto;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.entity.Type;
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

    @Cacheable(value = "pokemons", key = "#id")
    public void fetchAndSavePokemons(int limit) {
        for (int i = 1; i <= limit; i++) {
            String url = "https://pokeapi.co/api/v2/pokemon/" + i;

            PokemonDto pokemon = restTemplate.getForObject(url, PokemonDto.class);
            if (pokemon != null) {
                savePokemon(pokemon);
            }
        }
    }
    @Cacheable(value = "types", key = "#id")
    public void fetchAndSaveTypes(int limit) {
        for (int i = 1; i <= limit; i++) {
            String url = "https://pokeapi.co/api/v2/type/" + i;

            TypeDto type = restTemplate.getForObject(url, TypeDto.class);
            if (type != null) {
                saveType(type);
            }
        }
    }

    private void savePokemon(PokemonDto dto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setApiId(dto.id());
        pokemon.setName(dto.name());
        pokemon.setHeight(dto.height());
        pokemon.setWeight(dto.weight());
        pokemon.setBaseExperience(dto.base_experience());
        pokemonRepository.save(pokemon);
    }

    private void saveType(TypeDto dto) {
        Type type = new Type();

        typeRepository.save(type);
    }
}

