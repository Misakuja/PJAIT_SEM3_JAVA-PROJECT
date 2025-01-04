package pjatk.edu.pl.pokemon_integration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pjatk.edu.pl.pokemon_data.dto.*;
import pjatk.edu.pl.pokemon_data.dto.ResponseDto.*;
import pjatk.edu.pl.pokemon_data.entity.*;
import pjatk.edu.pl.pokemon_data.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class DatabaseService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);
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
        logger.info("Fetching and saving Pokemons from URL: {}", url);
        fetchAndSave(url, PokemonDto.class, this::savePokemon);
    }

    @Cacheable(value = "types")
    public void fetchAndSaveTypes() {
        String url = "https://pokeapi.co/api/v2/type/";
        logger.info("Fetching and saving Types from URL: {}", url);
        fetchAndSave(url, TypeDto.class, this::saveType);
    }

    @Cacheable(value = "moves")
    public void fetchAndSaveMoves() {
            String url = "https://pokeapi.co/api/v2/move/";
        logger.info("Fetching and saving Moves from URL: {}", url);
        fetchAndSave(url, MoveDto.class, this::saveMove);
    }

    @Cacheable(value = "items")
    public void fetchAndSaveItems() {
        String url = "https://pokeapi.co/api/v2/item/";
        logger.info("Fetching and saving Items from URL: {}", url);
        fetchAndSave(url, ItemDto.class, this::saveItem);
    }

    @Cacheable(value = "abilities")
    public void fetchAndSaveAbilities() {
        String url = "https://pokeapi.co/api/v2/ability/";
        logger.info("Fetching and saving Abilities from URL: {}", url);
        fetchAndSave(url, AbilityDto.class, this::saveAbility);
    }

    private <T> void fetchAndSave(String url, Class<T> dtoClass, Consumer<T> saveMethod) {
        ResponseDto response;
        do {
            logger.info("Fetching data from URL: {}", url);
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
        logger.info("Saving Pokemon with API ID: {}", dto.apiId());
        Optional<Pokemon> existingPokemon = pokemonRepository.findByApiId(dto.apiId());

        if (existingPokemon.isPresent()) {
            setPokemonValues(dto, existingPokemon.orElse(null));
        } else {
            Pokemon pokemon = new Pokemon();
            setPokemonValues(dto, pokemon);
        }
    }

    private void setPokemonValues(PokemonDto dto, Pokemon pokemon) {
        pokemon.setApiId(dto.apiId());
        pokemon.setName(dto.name());
        pokemon.setHeight(dto.height());
        pokemon.setWeight(dto.weight());
        pokemon.setBaseExperience(dto.baseExperience());


        List<Type> types = new ArrayList<>();
        for (TypeResponseDto typeEntry : dto.types()) {
            Type type = typeRepository.findByName(typeEntry.getType().name())
                    .orElseThrow(EntityNotFound::new);
            types.add(type);
        }

        List<Ability> abilities = new ArrayList<>();
        for (AbilityResponseDto abilityEntry : dto.abilities()) {
            Ability ability = abilityRepository.findByName(abilityEntry.getAbility().name())
                    .orElseThrow(EntityNotFound::new);
            abilities.add(ability);
        }

        List<Move> moves = new ArrayList<>();
        for (MoveResponseDto moveEntry : dto.moves()) {
            Move move = moveRepository.findByName(moveEntry.getMove().name())
                    .orElseThrow(EntityNotFound::new);
            moves.add(move);
        }

        pokemon.setTypes(types);
        pokemon.setAbilities(abilities);
        pokemon.setMoves(moves);

        pokemonRepository.save(pokemon);
        logger.info("Successfully saved Pokemon: {}", pokemon.getName());
    }

    private void saveType(TypeDto dto) {
        logger.info("Saving Type with API ID: {}", dto.apiId());
        Optional<Type> existingType = typeRepository.findByApiId(dto.apiId());

        if (existingType.isPresent()) {
            setTypeValues(dto, existingType.orElse(null));
        } else {
            Type type = new Type();
            setTypeValues(dto, type);
        }
    }

    private void setTypeValues(TypeDto dto, Type type) {
        type.setApiId(dto.apiId());
        type.setName(dto.name());
        typeRepository.save(type);
        logger.info("Successfully saved Type: {}", type.getName());
    }

    private void saveMove(MoveDto dto) {
        logger.info("Saving Move with API ID: {}", dto.apiId());
        Optional<Move> existingMove = moveRepository.findByApiId(dto.apiId());

        if (existingMove.isPresent()) {
            setMoveValues(dto, existingMove.orElse(null));
        } else {
            Move move = new Move();
            setMoveValues(dto, move);
        }
    }

    private void setMoveValues(MoveDto dto, Move move) {
        move.setApiId(dto.apiId());
        move.setName(dto.name());
        move.setPower(dto.power());
        move.setAccuracy(dto.accuracy());
        move.setPp(dto.pp());

        if (dto.type() != null) {
            Type type = typeRepository.findByName(dto.type().name())
                    .orElseThrow(EntityNotFound::new);
            move.setType(type);
        } else {
            logger.warn("Type is null for Move: {}", move.getName());
        }

        moveRepository.save(move);
        logger.info("Successfully saved Move: {}", move.getName());
    }

    private void saveAbility(AbilityDto dto) {
        logger.info("Saving Ability with API ID: {}", dto.apiId());
        Optional<Ability> existingAbility = abilityRepository.findByApiId(dto.apiId());

        if (existingAbility.isPresent()) {
            setMoveValues(dto, existingAbility.orElse(null));
        } else {
            Ability ability = new Ability();
            setMoveValues(dto, ability);
        }
    }

    private void setMoveValues(AbilityDto dto, Ability ability) {
        ability.setApiId(dto.apiId());
        ability.setName(dto.name());
        abilityRepository.save(ability);
        logger.info("Successfully saved Ability: {}", ability.getName());
    }

    private void saveItem(ItemDto dto) {
        logger.info("Saving Item with API ID: {}", dto.apiId());
        Optional<Item> existingItem = itemRepository.findByApiId(dto.apiId());
        if (existingItem.isPresent()) {
            setItemValues(dto, existingItem.orElse(null));
        } else {
            Item item = new Item();
            setItemValues(dto, item);
        }
    }

    private void setItemValues(ItemDto dto, Item item) {
        item.setApiId(dto.apiId());
        item.setName(dto.name());
        itemRepository.save(item);
        logger.info("Successfully saved Item: {}", item.getName());
    }

}

