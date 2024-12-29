package pjatk.edu.pl.pokemon_integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pjatk.edu.pl.pokemon_data.dto.*;
import pjatk.edu.pl.pokemon_data.entity.*;
import pjatk.edu.pl.pokemon_data.repository.*;

import java.util.List;

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

//    @Cacheable(value = "pokemons", key = "#id")
    public void fetchAndSavePokemons(int limit) {
        String url = "https://pokeapi.co/api/v2/pokemon/";
        ResponseDto response = restTemplate.getForObject(url, ResponseDto.class);

        if (response != null) {
            List<ResultDto> results = response.getResults();
            for (ResultDto result : results) {
                String pokemonUrl = result.getUrl();
                PokemonDto pokemon = restTemplate.getForObject(pokemonUrl, PokemonDto.class);
                if (pokemon != null) {
                    savePokemon(pokemon);
                }
            }
        }
    }
//    @Cacheable(value = "types", key = "#id")
    public void fetchAndSaveTypes(int limit) {
        for (int i = 1; i <= limit; i++) {
            String url = "https://pokeapi.co/api/v2/type/" + i;

            TypeDto type = restTemplate.getForObject(url, TypeDto.class);
            if (type != null) {
                saveType(type);
            }
        }
    }

    public void fetchAndSaveMoves(int limit) {
        for (int i = 1; i <= limit; i++) {
            String url = "https://pokeapi.co/api/v2/move/" + i;

            MoveDto move = restTemplate.getForObject(url, MoveDto.class);
            if (move != null) {
                saveMove(move);
            }
        }
    }

    public void fetchAndSaveAbilities(int limit) {
        for (int i = 1; i <= limit; i++) {
            String url = "https://pokeapi.co/api/v2/ability/" + i;

            AbilityDto ability = restTemplate.getForObject(url, AbilityDto.class);
            if (ability != null) {
                saveAbility(ability);
            }
        }
    }

    public void fetchAndSaveItems(int limit) {
        for (int i = 1; i <= limit; i++) {
            String url = "https://pokeapi.co/api/v2/item/" + i;

            ItemDto item = restTemplate.getForObject(url, ItemDto.class);
            if (item != null) {
                saveItem(item);
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

        List<Type> types = typeRepository.findAllById(dto.types().stream()
                .map(TypeDto::databaseId)
                .toList());

        List<Ability> abilities = abilityRepository.findAllById(dto.abilities().stream()
                .map(AbilityDto::databaseId)
                .toList());

        List<Move> moves = moveRepository.findAllById(dto.moves().stream()
                .map(MoveDto::databaseId)
                .toList());

        pokemon.setTypes(types);
        pokemon.setAbilities(abilities);
        pokemon.setMoves(moves);

        pokemonRepository.save(pokemon);
    }

    private void saveType(TypeDto dto) {
        Type type = new Type();
        type.setApiId(dto.id());
        type.setName(dto.name());
        typeRepository.save(type);
    }

    private void saveMove(MoveDto dto) {
        Move move = new Move();
        move.setApiId(dto.id());
        move.setName(dto.name());
        move.setPower(dto.power());
        move.setAccuracy(dto.accuracy());
        move.setPp(dto.pp());
        moveRepository.save(move);
    }

    private void saveAbility(AbilityDto dto) {
        Ability ability = new Ability();
        ability.setApiId(dto.id());
        ability.setName(dto.name());
        abilityRepository.save(ability);
    }

    private void saveItem(ItemDto dto) {
        Item item = new Item();
        item.setApiId(dto.id());
        item.setName(dto.name());
        itemRepository.save(item);
    }

}

