package pjatk.edu.pl.pokemon_integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import pjatk.edu.pl.pokemon_data.repository.*;
import pjatk.edu.pl.pokemon_integration.service.DatabaseService;

import static org.mockito.Mockito.mock;

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
        //??
    }



}
