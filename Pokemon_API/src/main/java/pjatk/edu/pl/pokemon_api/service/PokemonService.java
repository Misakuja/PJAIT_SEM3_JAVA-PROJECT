package pjatk.edu.pl.pokemon_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_data.entity.Ability;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.entity.Type;
import pjatk.edu.pl.pokemon_data.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_data.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.repository.PokemonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService extends BaseService<Pokemon> {
    private static final Logger logger = LoggerFactory.getLogger(PokemonService.class);

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository repository) {
        super(repository);
        this.pokemonRepository = repository;
    }

    public List<Pokemon> getAllPokemon() {
        return getAllEntities();
    }

    public List<Ability> getAllAbilitiesByPokemonId(Long pokemonId) {
        logger.info("Attempting to fetch all abilities of Pokémon by ID: {}", pokemonId);
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> {
            logger.warn("Failed to fetch all abilities of Pokémon with ID: {} not found.", pokemonId);
            return new EntityNotFound();
        });
        logger.info("Successfully fetched all abilities of Pokémon by ID: {}", pokemonId);
        return pokemon.getAbilities();
    }

    public List<Move> getAllMovesByPokemonId(Long pokemonId) {
        logger.info("Attempting to fetch all moves of Pokémon by ID: {}", pokemonId);
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> {
            logger.warn("Failed to fetch all moves of Pokémon with ID: {} not found.", pokemonId);
            return new EntityNotFound();
        });
        logger.info("Successfully fetched all moves of Pokémon by ID: {}", pokemonId);
        return pokemon.getMoves();
    }

    public List<Type> getAllTypesByPokemonId(Long pokemonId) {
        logger.info("Attempting to fetch all types of Pokémon by ID: {}", pokemonId);
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> {
                    logger.warn("Failed to fetch all types of Pokémon with ID: {} not found.", pokemonId);
                    return new EntityNotFound();
                });
        logger.info("Successfully fetched all types of Pokémon by ID: {}", pokemonId);
        return pokemon.getTypes();
    }

    public void deletePokemon(Long id) {
        deleteEntity(id);
    }

    public void addPokemon(Pokemon pokemon) {
        checkIfPokemonAlreadyExists(pokemon);
        addEntity(pokemon);
    }

    public void updatePokemon(Pokemon pokemon, Long id) {
        checkIfPokemonAlreadyExists(pokemon);
        updateEntity(pokemon, id);
    }

    public Pokemon getPokemonById(Long id) {
        return getEntityById(id);
    }

    public Pokemon getPokemonByApiId(Integer apiId) {
        logger.info("Fetching Pokémon by API ID: {}", apiId);
        return pokemonRepository.findByApiId(apiId).orElseThrow(() -> {
            logger.warn("Pokémon with API ID: {} not found.", apiId);
            return new EntityNotFound();
        });
    }

    public Pokemon getPokemonByName(String name) {
        logger.info("Fetching Pokémon by name: {}", name);
        return pokemonRepository.findByName(name).orElseThrow(() -> {
            logger.warn("Pokémon with name: {} not found.", name);
            return new EntityNotFound();
        });
    }

    public List<Pokemon> getPokemonByBaseExperience(Integer baseExperience) {
        logger.info("Fetching Pokémon by base experience: {}", baseExperience);
        List<Pokemon> pokemonList = pokemonRepository.findByBaseExperience(baseExperience);
        if (pokemonList.isEmpty()) {
            logger.warn("No Pokémon found with base experience: {}", baseExperience);
            throw new EntityNotFound();
        } else {
            logger.info("Successfully fetched {} Pokémon with base experience: {}", pokemonList.size(), baseExperience);
            return pokemonList;
        }
    }

    public List<Pokemon> getPokemonByHeight(Integer height) {
        logger.info("Fetching Pokémon by height: {}", height);
        List<Pokemon> pokemonList = pokemonRepository.findByHeight(height);
        if (pokemonList.isEmpty()) {
            logger.warn("No Pokémon found with height: {}", height);
            throw new EntityNotFound();
        } else {
            logger.info("Successfully fetched {} Pokémon with height: {}", pokemonList.size(), height);
            return pokemonList;
        }
    }

    public List<Pokemon> getPokemonByWeight(Integer weight) {
        logger.info("Fetching Pokémon by weight: {}", weight);
        List<Pokemon> pokemonList = pokemonRepository.findByWeight(weight);
        if (pokemonList.isEmpty()) {
            logger.warn("No Pokémon found with weight: {}", weight);
            throw new EntityNotFound();
        } else {
            logger.info("Successfully fetched {} Pokémon with weight: {}", pokemonList.size(), weight);
            return pokemonList;
        }
    }

    private void checkIfPokemonAlreadyExists(Pokemon pokemon) {
        logger.info("Checking if Pokémon already exists: {}", pokemon);
        Optional<Pokemon> existingPokemon = pokemonRepository.findByName(pokemon.getName());
        if (existingPokemon.isPresent()) {
            logger.warn("Pokémon with name: {} already exists.", pokemon.getName());
            throw new EntityAlreadyExists();
        }
        logger.info("Pokémon with name: {} does not exist. Proceeding to add.", pokemon.getName());
    }


}


//    public List<Pokemon> getAllAbilitiesByPokemonId(Long id) {
//                Pokemon pokemon = pokemonRepository.findById(pokemonId)
//                .orElseThrow(() -> new IllegalArgumentException("Pokemon not found with ID: " + pokemonId));
//
//        // Get ability names
//        return pokemon.getAbilities()
//                .stream()
//                .map(ability -> ability.getName())
//                .collect(Collectors.toList());
//    }
//    }
//
//    public List<Pokemon> getAllMovesByPokemonId(Long id) {
//        return getEntityListByField("/pokemon/weight/" + weight, weight);
//    }
//
//    public List<Pokemon> getAllTypesByPokemonId(Long id) {
//        return getEntityListByField("/pokemon/weight/" + weight, weight);
//    }
//TODO: get all types, get all abilities, get all moves (once tables fixed)