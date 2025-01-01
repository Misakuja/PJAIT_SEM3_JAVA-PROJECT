package pjatk.edu.pl.pokemon_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.repository.*;

import java.util.List;

@Service
public class PokemonService extends BaseService<Pokemon> {
    @Autowired
    public PokemonService(PokemonRepository repository) {
        super(repository);
    }

    public List<Pokemon> getAllPokemon() {
        return getAllEntities();
    }

    public void deletePokemon(Long id) {
        deleteEntity(id);
    }

    public void addPokemon(Pokemon pokemon) {
        addEntity(pokemon);
    }

    public void updatePokemon(Pokemon pokemon, Long id) {
        updateEntity(pokemon, id);
    }

    public Pokemon getPokemonById(Long id) {
        return getEntityById(id);
    }
}


// TODO: ABILITY
// GET BY NAME
// GET BY API ID

// TODO: ITEM
// GET BY NAME
// GET BY API ID

// TODO: TYPE
// GET BY API ID
// GET BY NAME

// TODO: MOVE
// GET BY ACCURACY
// GET BY API ID
// GET BY NAME
// GET BY POWER
// GET BY PP

// TODO: POKEMON
// GET BY API ID
// GET BY BASE EXPERIENCE
// GET BY HEIGHT
// GET BY NAME
// GET BY WEIGHT

// get all types, get all abilities, get all moves (once tables fixed)