package pjatk.edu.pl.pokemon_api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pjatk.edu.pl.pokemon_api.service.PokemonService;
import pjatk.edu.pl.pokemon_api.service.TypeService;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.entity.Type;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<List<Pokemon>> getAllItems() {
        List<Pokemon> pokemons = pokemonService.getAllPokemon();
        return ResponseEntity.ok(pokemons);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> addPokemon(@RequestBody Pokemon pokemon) {
        pokemonService.addPokemon(pokemon);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable Long id) {
        pokemonService.updatePokemon(pokemon, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id) {
        Pokemon pokemon = pokemonService.getPokemonById(id);
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/apiId/{apiId}")
    public ResponseEntity<Pokemon> getPokemonByApiId(@PathVariable Integer apiId) {
        Pokemon pokemon = pokemonService.getPokemonByApiId(apiId);
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Pokemon> getPokemonByName(@PathVariable String name) {
        Pokemon pokemon = pokemonService.getPokemonByName(name);
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/baseExperience/{baseExperience}")
    public ResponseEntity<List<Pokemon>> getPokemonByBaseExperience(@PathVariable Integer baseExperience) {
        List<Pokemon> pokemons = pokemonService.getPokemonByBaseExperience(baseExperience);
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/height/{height}")
    public ResponseEntity<List<Pokemon>> getPokemonByHeight(@PathVariable Integer height) {
        List<Pokemon> pokemons = pokemonService.getPokemonByHeight(height);
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/weight/{weight}")
    public ResponseEntity<List<Pokemon>> getPokemonByWeight(@PathVariable Integer weight) {
        List<Pokemon> pokemons = pokemonService.getPokemonByWeight(weight);
        return ResponseEntity.ok(pokemons);
    }

//    @GetMapping("/weight/{weight}")
//    public ResponseEntity<List<Pokemon>> getPokemonById(@PathVariable int weight) {
//        List<Pokemon> pokemon = pokemonService.getPokemonByWeight(weight);
//        return ResponseEntity.ok(pokemon);
//    }
}



