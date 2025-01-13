package pjatk.edu.pl.pokemon_api.unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import pjatk.edu.pl.pokemon_api.service.AbilityService;
import pjatk.edu.pl.pokemon_data.entity.Ability;
import pjatk.edu.pl.pokemon_data.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_data.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.repository.AbilityRepository;
import pjatk.edu.pl.pokemon_data.repository.PokemonRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AbilityServiceTest {

    @Mock
    private AbilityRepository abilityRepositoryMock;

    @Mock
    private PokemonRepository pokemonRepositoryMock;

    @Mock
    private Logger loggerMock;

    private AbilityService abilityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        abilityService = new AbilityService(abilityRepositoryMock, pokemonRepositoryMock, loggerMock);
    }

    @Test
    public void deleteAbilityCallsFindAllByAbilityIdTest() {
        Long abilityId = 1L;
        Ability ability = new Ability();

        when(pokemonRepositoryMock.findAllByAbilityId(abilityId)).thenReturn(List.of());
        when(abilityRepositoryMock.findById(abilityId)).thenReturn(Optional.of(ability));

        abilityService.deleteAbility(abilityId);

        verify(pokemonRepositoryMock).findAllByAbilityId(abilityId);
        verify(pokemonRepositoryMock, times(0)).save(any());
    }

    @Test
    public void addAbilityThrowsEntityAlreadyExistsTest() {
        Ability ability = new Ability();
        ability.setName("test");

        when(abilityRepositoryMock.findByName("test")).thenReturn(Optional.of(ability));

        assertThrows(EntityAlreadyExists.class, () -> abilityService.addAbility(ability));
        verify(loggerMock).info("Checking if ability already exists: {}", ability);
        verify(loggerMock).warn("Ability with name: {} already exists.", ability.getName());
    }

    @Test
    public void updateAbilityThrowsEntityAlreadyExistsTest() {
        Ability ability = new Ability();
        ability.setName("test");

        when(abilityRepositoryMock.findByName("test")).thenReturn(Optional.of(ability));

        assertThrows(EntityAlreadyExists.class, () -> abilityService.updateAbility(ability, 1L));
        verify(loggerMock).info("Checking if ability already exists: {}", ability);
        verify(loggerMock).warn("Ability with name: {} already exists.", ability.getName());
    }

    @Test
    public void getAbilityByApiIdWorksCorrectlyTest() {
        Integer apiId = 1001;
        Ability expectedAbility = new Ability();
        expectedAbility.setId(1L);
        expectedAbility.setApiId(apiId);

        when(abilityRepositoryMock.findByApiId(apiId)).thenReturn(Optional.of(expectedAbility));

        Ability result = abilityService.getAbilityByApiId(apiId);

        assertNotNull(result);
        assertEquals(apiId, result.getApiId());
        verify(abilityRepositoryMock).findByApiId(apiId);
        verify(loggerMock).info("Fetching ability by API ID: {}", apiId);
    }

    @Test
    public void getAbilityByApiIdThrowsEntityNotFoundTest() {
        Integer apiId = 1001;

        when(abilityRepositoryMock.findByApiId(apiId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> abilityService.getAbilityByApiId(apiId));
        verify(abilityRepositoryMock).findByApiId(apiId);
        verify(loggerMock).info("Fetching ability by API ID: {}", apiId);
        verify(loggerMock).warn("Ability with API ID: {} not found.", apiId);
    }

    @Test
    public void getAbilityByNameWorksCorrectlyTest() {
        String name = "test";
        Ability expectedAbility = new Ability();
        expectedAbility.setId(1L);
        expectedAbility.setName(name);

        when(abilityRepositoryMock.findByName(name)).thenReturn(Optional.of(expectedAbility));

        Ability result = abilityService.getAbilityByName(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
        verify(abilityRepositoryMock).findByName(name);
        verify(loggerMock).info("Fetching ability by name: {}", name);
    }

    @Test
    public void getAbilityByNameThrowsEntityNotFoundTest() {
        String name = "name";

        when(abilityRepositoryMock.findByName(name)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> abilityService.getAbilityByName(name));
        verify(abilityRepositoryMock).findByName(name);
        verify(loggerMock).info("Fetching ability by name: {}", name);
        verify(loggerMock).warn("Ability with name: {} not found.", name);
    }
}