package pjatk.edu.pl.pokemon_api.unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import pjatk.edu.pl.pokemon_api.service.PokemonService;
import pjatk.edu.pl.pokemon_data.entity.Ability;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.entity.Type;
import pjatk.edu.pl.pokemon_data.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_data.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.repository.PokemonRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PokemonServiceTest {
    @Mock
    private PokemonRepository pokemonRepositoryMock;

    @Mock
    private Logger loggerMock;

    private PokemonService pokemonService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pokemonService = new PokemonService(pokemonRepositoryMock, loggerMock);
    }

    @Test
    public void addPokemonThrowsEntityAlreadyExistsTest() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("test");

        when(pokemonRepositoryMock.findByName("test")).thenReturn(Optional.of(pokemon));

        assertThrows(EntityAlreadyExists.class, () -> pokemonService.addPokemon(pokemon));
        verify(loggerMock).info("Checking if Pokémon already exists: {}", pokemon);
        verify(loggerMock).warn("Pokémon with name: {} already exists.", pokemon.getName());
    }

    @Test
    public void updatePokemonThrowsEntityAlreadyExistsTest() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("test");

        when(pokemonRepositoryMock.findByName("test")).thenReturn(Optional.of(pokemon));

        assertThrows(EntityAlreadyExists.class, () -> pokemonService.updatePokemon(pokemon, 1L));
        verify(loggerMock).info("Checking if Pokémon already exists: {}", pokemon);
        verify(loggerMock).warn("Pokémon with name: {} already exists.", pokemon.getName());
    }

    @Test
    public void getPokemonByApiIdWorksCorrectlyTest() {
        Integer apiId = 1001;
        Pokemon expectedPokemon = new Pokemon();
        expectedPokemon.setId(1L);
        expectedPokemon.setApiId(apiId);

        when(pokemonRepositoryMock.findByApiId(apiId)).thenReturn(Optional.of(expectedPokemon));

        Pokemon result = pokemonService.getPokemonByApiId(apiId);

        assertNotNull(result);
        assertEquals(apiId, result.getApiId());
        verify(pokemonRepositoryMock).findByApiId(apiId);
        verify(loggerMock).info("Fetching Pokémon by API ID: {}", apiId);
    }

    @Test
    public void getPokemonByApiIdThrowsEntityNotFoundTest() {
        Integer apiId = 1001;

        when(pokemonRepositoryMock.findByApiId(apiId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> pokemonService.getPokemonByApiId(apiId));
        verify(pokemonRepositoryMock).findByApiId(apiId);
        verify(loggerMock).info("Fetching Pokémon by API ID: {}", apiId);
        verify(loggerMock).warn("Pokémon with API ID: {} not found.", apiId);
    }

    @Test
    public void getPokemonByNameWorksCorrectlyTest() {
        String name = "test";
        Pokemon expectedPokemon = new Pokemon();
        expectedPokemon.setId(1L);
        expectedPokemon.setName(name);

        when(pokemonRepositoryMock.findByName(name)).thenReturn(Optional.of(expectedPokemon));

        Pokemon result = pokemonService.getPokemonByName(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
        verify(pokemonRepositoryMock).findByName(name);
        verify(loggerMock).info("Fetching Pokémon by name: {}", name);
    }

    @Test
    public void getPokemonByNameThrowsEntityNotFoundTest() {
        String name = "name";

        when(pokemonRepositoryMock.findByName(name)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> pokemonService.getPokemonByName(name));
        verify(pokemonRepositoryMock).findByName(name);
        verify(loggerMock).info("Fetching Pokémon by name: {}", name);
        verify(loggerMock).warn("Pokémon with name: {} not found.", name);
    }

    @Test
    public void getPokemonByBaseExperienceWorksCorrectlyTest() {
        Integer baseExperience = 100;
        List<Pokemon> expectedPokemon = List.of(new Pokemon());
        expectedPokemon.getFirst().setBaseExperience(baseExperience);

        when(pokemonRepositoryMock.findByBaseExperience(baseExperience)).thenReturn(expectedPokemon);

        List<Pokemon> result = pokemonService.getPokemonByBaseExperience(baseExperience);

        assertEquals(expectedPokemon.getFirst(), result.getFirst());
        verify(pokemonRepositoryMock).findByBaseExperience(baseExperience);
        verify(loggerMock).info("Fetching Pokémon by base experience: {}", baseExperience);
    }

    @Test
    public void getPokemonByBaseExperienceThrowsEntityNotFoundTest() {
        Integer baseExperience = 100;

        when(pokemonRepositoryMock.findByBaseExperience(baseExperience)).thenReturn(List.of());

        assertThrows(EntityNotFound.class, () -> pokemonService.getPokemonByBaseExperience(baseExperience));
        verify(pokemonRepositoryMock).findByBaseExperience(baseExperience);
        verify(loggerMock).info("Fetching Pokémon by base experience: {}", baseExperience);
        verify(loggerMock).warn("No Pokémon found with base experience: {}", baseExperience);
    }

    @Test
    public void getPokemonByWeightWorksCorrectlyTest() {
        Integer weight = 100;
        List<Pokemon> expectedPokemon = List.of(new Pokemon());
        expectedPokemon.getFirst().setWeight(weight);

        when(pokemonRepositoryMock.findByWeight(weight)).thenReturn(expectedPokemon);

        List<Pokemon> result = pokemonService.getPokemonByWeight(weight);

        assertEquals(expectedPokemon.getFirst(), result.getFirst());
        verify(pokemonRepositoryMock).findByWeight(weight);
        verify(loggerMock).info("Fetching Pokémon by weight: {}", weight);
    }

    @Test
    public void getPokemonByWeightThrowsEntityNotFoundTest() {
        Integer weight = 100;

        when(pokemonRepositoryMock.findByWeight(weight)).thenReturn(List.of());

        assertThrows(EntityNotFound.class, () -> pokemonService.getPokemonByWeight(weight));
        verify(pokemonRepositoryMock).findByWeight(weight);
        verify(loggerMock).info("Fetching Pokémon by weight: {}", weight);
        verify(loggerMock).warn("No Pokémon found with weight: {}", weight);
    }

    @Test
    public void getPokemonByHeightWorksCorrectlyTest() {
        Integer height = 100;
        List<Pokemon> expectedPokemon = List.of(new Pokemon());
        expectedPokemon.getFirst().setHeight(height);

        when(pokemonRepositoryMock.findByHeight(height)).thenReturn(expectedPokemon);

        List<Pokemon> result = pokemonService.getPokemonByHeight(height);

        assertEquals(expectedPokemon.getFirst(), result.getFirst());
        verify(pokemonRepositoryMock).findByHeight(height);
        verify(loggerMock).info("Fetching Pokémon by height: {}", height);
    }

    @Test
    public void getPokemonByHeightThrowsEntityNotFoundTest() {
        Integer height = 100;

        when(pokemonRepositoryMock.findByHeight(height)).thenReturn(List.of());

        assertThrows(EntityNotFound.class, () -> pokemonService.getPokemonByHeight(height));
        verify(pokemonRepositoryMock).findByHeight(height);
        verify(loggerMock).info("Fetching Pokémon by height: {}", height);
        verify(loggerMock).warn("No Pokémon found with height: {}", height);
    }

    @Test
    public void getAllAbilitiesByPokemonIdWorksCorrectlyTest() {
        Long pokemonId = 1L;
        Pokemon mockPokemon = new Pokemon();
        Ability ability1 = new Ability();
        ability1.setName("test1");
        Ability ability2 = new Ability();
        ability2.setName("test2");
        mockPokemon.setAbilities(Arrays.asList(ability1, ability2));

        when(pokemonRepositoryMock.findById(pokemonId)).thenReturn(Optional.of(mockPokemon));

        List<Ability> abilities = pokemonService.getAllAbilitiesByPokemonId(pokemonId);

        assertEquals(2, abilities.size());
        assertEquals("test1", abilities.get(0).getName());
        assertEquals("test2", abilities.get(1).getName());

        verify(pokemonRepositoryMock).findById(pokemonId);
        verify(loggerMock).info("Attempting to fetch all abilities of Pokémon by ID: {}", pokemonId);
        verify(loggerMock).info("Successfully fetched all abilities of Pokémon by ID: {}", pokemonId);
    }

    @Test
    public void getAllAbilitiesByPokemonIdThrowsEntityNotFoundTest() {
        Long pokemonId = 1L;

        when(pokemonRepositoryMock.findById(pokemonId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> pokemonService.getAllAbilitiesByPokemonId(pokemonId));

        verify(pokemonRepositoryMock).findById(pokemonId);
        verify(loggerMock).info("Attempting to fetch all abilities of Pokémon by ID: {}", pokemonId);
        verify(loggerMock).warn("Failed to fetch all abilities of Pokémon with ID: {} not found.", pokemonId);
    }

    @Test
    public void getAllMovesByPokemonIdWorksCorrectlyTest() {
        Long pokemonId = 2L;
        Pokemon mockPokemon = new Pokemon();
        Move move1 = new Move();
        move1.setName("test1");
        Move move2 = new Move();
        move2.setName("test2");
        mockPokemon.setMoves(Arrays.asList(move1, move2));

        when(pokemonRepositoryMock.findById(pokemonId)).thenReturn(Optional.of(mockPokemon));

        List<Move> moves = pokemonService.getAllMovesByPokemonId(pokemonId);

        assertEquals(2, moves.size());
        assertEquals("test1", moves.get(0).getName());
        assertEquals("test2", moves.get(1).getName());

        verify(pokemonRepositoryMock).findById(pokemonId);
        verify(loggerMock).info("Attempting to fetch all moves of Pokémon by ID: {}", pokemonId);
        verify(loggerMock).info("Successfully fetched all moves of Pokémon by ID: {}", pokemonId);
    }

    @Test
    public void getAllMovesByPokemonIdThrowsEntityNotFoundTest() {
        Long pokemonId = 2L;

        when(pokemonRepositoryMock.findById(pokemonId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> pokemonService.getAllMovesByPokemonId(pokemonId));

        verify(pokemonRepositoryMock).findById(pokemonId);
        verify(loggerMock).info("Attempting to fetch all moves of Pokémon by ID: {}", pokemonId);
        verify(loggerMock).warn("Failed to fetch all moves of Pokémon with ID: {} not found.", pokemonId);
    }

    @Test
    public void getAllTypesByPokemonIdWorksCorrectlyTest() {
        Long pokemonId = 3L;
        Pokemon mockPokemon = new Pokemon();
        Type type1 = new Type();
        type1.setName("test1");
        Type type2 = new Type();
        type2.setName("test2");
        mockPokemon.setTypes(Arrays.asList(type1, type2));

        when(pokemonRepositoryMock.findById(pokemonId)).thenReturn(Optional.of(mockPokemon));

        List<Type> types = pokemonService.getAllTypesByPokemonId(pokemonId);

        assertEquals(2, types.size());
        assertEquals("test1", types.get(0).getName());
        assertEquals("test2", types.get(1).getName());

        verify(pokemonRepositoryMock).findById(pokemonId);
        verify(loggerMock).info("Attempting to fetch all types of Pokémon by ID: {}", pokemonId);
        verify(loggerMock).info("Successfully fetched all types of Pokémon by ID: {}", pokemonId);
    }

    @Test
    public void getAllTypesByPokemonIdThrowsEntityNotFoundTest() {
        Long pokemonId = 3L;

        when(pokemonRepositoryMock.findById(pokemonId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> pokemonService.getAllTypesByPokemonId(pokemonId));

        verify(pokemonRepositoryMock).findById(pokemonId);
        verify(loggerMock).info("Attempting to fetch all types of Pokémon by ID: {}", pokemonId);
        verify(loggerMock).warn("Failed to fetch all types of Pokémon with ID: {} not found.", pokemonId);
    }
}