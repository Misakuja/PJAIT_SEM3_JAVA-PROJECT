package pjatk.edu.pl.pokemon_integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import pjatk.edu.pl.pokemon_data.dto.PokemonDto;
import pjatk.edu.pl.pokemon_data.dto.ResponseDto.*;
import pjatk.edu.pl.pokemon_data.entity.Ability;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.entity.Type;
import pjatk.edu.pl.pokemon_data.repository.*;
import pjatk.edu.pl.pokemon_integration.service.DatabaseService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DatabaseServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private PokemonRepository pokemonRepository;

    @Mock
    private AbilityRepository abilityRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private MoveRepository moveRepository;

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private DatabaseService databaseService;

    @BeforeEach
    void setup() {
        this.pokemonRepository = mock(PokemonRepository.class);
        this.abilityRepository = mock(AbilityRepository.class);
        this.itemRepository = mock(ItemRepository.class);
        this.moveRepository = mock(MoveRepository.class);
        this.typeRepository = mock(TypeRepository.class);
        this.restTemplate = mock(RestTemplate.class);
        this.databaseService = new DatabaseService(restTemplate, pokemonRepository, abilityRepository, itemRepository, moveRepository, typeRepository);
    }

    @Test
    void fetchAndSavePokemonsFetchesDataFromAPIAndSavesToRepository() {
        String url = "https://pokeapi.co/api/v2/pokemon/";

        ResponseDto responseDto = new ResponseDto();

        ResultDto resultDto = new ResultDto();
        resultDto.setUrl("https://pokeapi.co/api/v2/pokemon/1/");
        responseDto.setResults(List.of(resultDto));
        responseDto.setNext(null);

        List<TypeResponseDto> types = List.of(new TypeResponseDto());
        List<AbilityResponseDto> abilities = List.of(new AbilityResponseDto());
        List<MoveResponseDto> moves = List.of(new MoveResponseDto());

        PokemonDto pokemonDto = new PokemonDto(1L, 10, "Pikachu", 20, 100, 10, types, abilities, moves);

        when(restTemplate.getForObject(eq(url), eq(ResponseDto.class))).thenReturn(responseDto);

        when(restTemplate.getForObject(eq("https://pokeapi.co/api/v2/pokemon/1/"), eq(PokemonDto.class))).thenReturn(pokemonDto);

        when(typeRepository.findByName(anyString())).thenReturn(Optional.of(new Type()));
        when(abilityRepository.findByName(anyString())).thenReturn(Optional.of(new Ability()));
        when(moveRepository.findByName(anyString())).thenReturn(Optional.of(new Move()));

        databaseService.fetchAndSavePokemons();

        verify(pokemonRepository).save(any(Pokemon.class));
    }
}
