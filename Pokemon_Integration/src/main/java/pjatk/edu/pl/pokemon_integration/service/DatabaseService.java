package pjatk.edu.pl.pokemon_integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pjatk.edu.pl.pokemon_data.dto.*;
import pjatk.edu.pl.pokemon_data.entity.*;
import pjatk.edu.pl.pokemon_data.repository.*;

import java.util.List;
import java.util.function.Consumer;

//TODO: FIX JOINS SO THAT JOIN TABLES GET FILLED OUT, AS WELL AS FK IN MOVE TABLE

@Service
public class DatabaseService {
    private final RestTemplate restTemplate;
    private final PokemonRepository pokemonRepository;
    private final AbilityRepository abilityRepository;
    private final ItemRepository itemRepository;
    private final MoveRepository moveRepository;
    private final TypeRepository typeRepository;

    @Autowired
    public DatabaseService(RestTemplate restTemplate, PokemonRepository pokemonRepository, AbilityRepository abilityRepository, ItemRepository itemRepository, MoveRepository moveRepository, TypeRepository typeRepository) {
        this.restTemplate = restTemplate;
        this.pokemonRepository = pokemonRepository;
        this.abilityRepository = abilityRepository;
        this.itemRepository = itemRepository;
        this.moveRepository = moveRepository;
        this.typeRepository = typeRepository;
    }
    @Cacheable(value = "pokemons")
    public void fetchAndSavePokemons() {
        String url = "https://pokeapi.co/api/v2/pokemon/";
        fetchAndSave(url, PokemonDto.class, this::savePokemon);
    }

    @Cacheable(value = "types")
    public void fetchAndSaveTypes() {
        String url = "https://pokeapi.co/api/v2/type/";
        fetchAndSave(url, TypeDto.class, this::saveType);
    }

    @Cacheable(value = "moves")
    public void fetchAndSaveMoves() {
            String url = "https://pokeapi.co/api/v2/move/";
            fetchAndSave(url, MoveDto.class, this::saveMove);
    }

    @Cacheable(value = "items")
    public void fetchAndSaveItems() {
        String url = "https://pokeapi.co/api/v2/item/";
        fetchAndSave(url, ItemDto.class, this::saveItem);
    }

    @Cacheable(value = "abilities")
    public void fetchAndSaveAbilities() {
        String url = "https://pokeapi.co/api/v2/ability/";
        fetchAndSave(url, AbilityDto.class, this::saveAbility);
    }

    private <T> void fetchAndSave(String url, Class<T> dtoClass, Consumer<T> saveMethod) {
        ResponseDto response;
        do {
            response = restTemplate.getForObject(url, ResponseDto.class);

            if (response != null) {
                List<ResultDto> results = response.getResults();
                for (ResultDto result : results) {
                    String nextIndexUrl = result.getUrl();
                    T item = restTemplate.getForObject(nextIndexUrl, dtoClass);
                    if (item != null) {
                        saveMethod.accept(item);
                    }
                }
            }
            url = response.getNext();
        } while (url != null);
    }

    private void savePokemon(PokemonDto dto) {
        Pokemon existingPokemon = pokemonRepository.findByApiId(dto.id());

        if (existingPokemon != null) {
            setPokemonValues(dto, existingPokemon);
        } else {
            Pokemon pokemon = new Pokemon();
            setPokemonValues(dto, pokemon);
        }
    }

    private void setPokemonValues(PokemonDto dto, Pokemon pokemon) {
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
        Type existingType = typeRepository.findByApiId(dto.id());

        if (existingType != null) {
            setTypeValues(dto, existingType);
        } else {
            Type type = new Type();
            setTypeValues(dto, type);
        }
    }

    private void setTypeValues(TypeDto dto, Type type) {
        type.setApiId(dto.id());
        type.setName(dto.name());
        typeRepository.save(type);
    }

    private void saveMove(MoveDto dto) {
        Move existingMove = moveRepository.findByApiId(dto.id());

        if (existingMove != null) {
            setMoveValues(dto, existingMove);
        } else {
            Move move = new Move();
            setMoveValues(dto, move);
        }
    }

    private void setMoveValues(MoveDto dto, Move move) {
        move.setApiId(dto.id());
        move.setName(dto.name());
        move.setPower(dto.power());
        move.setAccuracy(dto.accuracy());
        move.setPp(dto.pp());

        moveRepository.save(move);
    }

    private void saveAbility(AbilityDto dto) {
        Ability existingAbility = abilityRepository.findByApiId(dto.id());

        if (existingAbility != null) {
            setMoveValues(dto, existingAbility);
        } else {
            Ability ability = new Ability();
            setMoveValues(dto, ability);
        }
    }

    private void setMoveValues(AbilityDto dto, Ability ability) {
        ability.setApiId(dto.id());
        ability.setName(dto.name());
        abilityRepository.save(ability);
    }

    private void saveItem(ItemDto dto) {
        Item existingItem = itemRepository.findByApiId(dto.id());
        if (existingItem != null) {
            setItemValues(dto, existingItem);
        } else {
            Item item = new Item();
            setItemValues(dto, item);
        }
    }

    private void setItemValues(ItemDto dto, Item item) {
        item.setApiId(dto.id());
        item.setName(dto.name());
        itemRepository.save(item);
    }

}

