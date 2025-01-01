package pjatk.edu.pl.pokemon_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_api.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_api.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService extends BaseService<Pokemon> {
    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository repository) {
        super(repository);
        this.pokemonRepository = repository;
    }

    public List<Pokemon> getAllPokemon() {
        return getAllEntities();
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
        return pokemonRepository.findByApiId(apiId).orElseThrow(EntityNotFound::new);
    }

    public Pokemon getPokemonByName(String name) {
        return pokemonRepository.findByName(name).orElseThrow(EntityNotFound::new);
    }

    public List<Pokemon> getPokemonByBaseExperience(Integer baseExperience) {
        List<Pokemon> pokemonList = pokemonRepository.findByBaseExperience(baseExperience);
        if (pokemonList.isEmpty()) throw new EntityNotFound();
        else return pokemonList;
    }

    public List<Pokemon> getPokemonByHeight(Integer height) {
        List<Pokemon> pokemonList = pokemonRepository.findByHeight(height);
        if (pokemonList.isEmpty()) throw new EntityNotFound();
        else return pokemonList;
    }

    public List<Pokemon> getPokemonByWeight(Integer weight) {
        List<Pokemon> pokemonList = pokemonRepository.findByWeight(weight);
        if (pokemonList.isEmpty()) throw new EntityNotFound();
        else return pokemonList;
    }


    private void checkIfPokemonAlreadyExists(Pokemon pokemon) {
        Optional<Pokemon> existingPokemon = pokemonRepository.findByName(pokemon.getName());
        if (existingPokemon.isPresent()) throw new EntityAlreadyExists();
    }

//    public List<Pokemon> getPokemonByWeight(int weight) {
//        return getEntityByField("weight", weight);
//    }
}


//TODO: get all types, get all abilities, get all moves (once tables fixed)