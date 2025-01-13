package pjatk.edu.pl.pokemon_api.unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import pjatk.edu.pl.pokemon_api.service.TypeService;
import pjatk.edu.pl.pokemon_data.entity.Type;
import pjatk.edu.pl.pokemon_data.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_data.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.repository.MoveRepository;
import pjatk.edu.pl.pokemon_data.repository.PokemonRepository;
import pjatk.edu.pl.pokemon_data.repository.TypeRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TypeServiceTest {

    @Mock
    private TypeRepository typeRepositoryMock;

    @Mock
    private MoveRepository moveRepositoryMock;

    @Mock
    private PokemonRepository pokemonRepositoryMock;

    @Mock
    private Logger loggerMock;

    private TypeService typeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        typeService = new TypeService(typeRepositoryMock, moveRepositoryMock, pokemonRepositoryMock, loggerMock);
    }

    @Test
    public void deleteTypeCallsFindAllByTypeIdTest() {
        Long typeId = 1L;
        Type type = new Type();

        when(pokemonRepositoryMock.findAllByTypeId(typeId)).thenReturn(List.of());
        when(moveRepositoryMock.findAllByTypeId(typeId)).thenReturn(List.of());
        when(typeRepositoryMock.findById(typeId)).thenReturn(Optional.of(type));

        typeService.deleteType(typeId);

        verify(pokemonRepositoryMock).findAllByTypeId(typeId);
        verify(moveRepositoryMock).findAllByTypeId(typeId);
        verify(pokemonRepositoryMock, times(0)).save(any());
        verify(moveRepositoryMock, times(0)).save(any());
    }

    @Test
    public void addTypeThrowsEntityAlreadyExistsTest() {
        Type type = new Type();
        type.setName("test");

        when(typeRepositoryMock.findByName("test")).thenReturn(Optional.of(type));

        assertThrows(EntityAlreadyExists.class, () -> typeService.addType(type));
        verify(loggerMock).info("Checking if type already exists: {}", type);
        verify(loggerMock).warn("Type with name: {} already exists.", type.getName());
    }

    @Test
    public void updateTypeThrowsEntityAlreadyExistsTest() {
        Type type = new Type();
        type.setName("test");

        when(typeRepositoryMock.findByName("test")).thenReturn(Optional.of(type));

        assertThrows(EntityAlreadyExists.class, () -> typeService.updateType(type, 1L));
        verify(loggerMock).info("Checking if type already exists: {}", type);
        verify(loggerMock).warn("Type with name: {} already exists.", type.getName());
    }

    @Test
    public void getTypeByApiIdWorksCorrectlyTest() {
        Integer apiId = 1001;
        Type expectedType = new Type();
        expectedType.setId(1L);
        expectedType.setApiId(apiId);

        when(typeRepositoryMock.findByApiId(apiId)).thenReturn(Optional.of(expectedType));

        Type result = typeService.getTypeByApiId(apiId);

        assertNotNull(result);
        assertEquals(apiId, result.getApiId());
        verify(typeRepositoryMock).findByApiId(apiId);
        verify(loggerMock).info("Fetching type by API ID: {}", apiId);
    }

    @Test
    public void getTypeByApiIdThrowsEntityNotFoundTest() {
        Integer apiId = 1001;

        when(typeRepositoryMock.findByApiId(apiId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> typeService.getTypeByApiId(apiId));
        verify(typeRepositoryMock).findByApiId(apiId);
        verify(loggerMock).info("Fetching type by API ID: {}", apiId);
        verify(loggerMock).warn("Type with API ID: {} not found.", apiId);
    }

    @Test
    public void getTypeByNameWorksCorrectlyTest() {
        String name = "test";
        Type expectedType = new Type();
        expectedType.setId(1L);
        expectedType.setName(name);

        when(typeRepositoryMock.findByName(name)).thenReturn(Optional.of(expectedType));

        Type result = typeService.getTypeByName(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
        verify(typeRepositoryMock).findByName(name);
        verify(loggerMock).info("Fetching type by name: {}", name);
    }

    @Test
    public void getTypeByNameThrowsEntityNotFoundTest() {
        String name = "name";

        when(typeRepositoryMock.findByName(name)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> typeService.getTypeByName(name));
        verify(typeRepositoryMock).findByName(name);
        verify(loggerMock).info("Fetching type by name: {}", name);
        verify(loggerMock).warn("Type with name: {} not found.", name);
    }
}