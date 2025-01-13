package pjatk.edu.pl.pokemon_api.unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import pjatk.edu.pl.pokemon_api.service.MoveService;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Type;
import pjatk.edu.pl.pokemon_data.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_data.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.repository.MoveRepository;
import pjatk.edu.pl.pokemon_data.repository.PokemonRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MoveServiceTest {

    @Mock
    private MoveRepository moveRepositoryMock;


    @Mock
    private PokemonRepository pokemonRepositoryMock;

    @Mock
    private Logger loggerMock;

    private MoveService moveService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        moveService = new MoveService(moveRepositoryMock, pokemonRepositoryMock, loggerMock);
    }

    @Test
    public void deleteMoveCallsFindAllByMoveIdTest() {
        Long moveId = 1L;
        Move move = new Move();

        when(pokemonRepositoryMock.findAllByMoveId(moveId)).thenReturn(List.of());
        when(moveRepositoryMock.findById(moveId)).thenReturn(Optional.of(move));

        moveService.deleteMove(moveId);

        verify(pokemonRepositoryMock).findAllByMoveId(moveId);
        verify(pokemonRepositoryMock, times(0)).save(any());
    }

    @Test
    public void addMoveThrowsEntityAlreadyExistsTest() {
        Move move = new Move();
        move.setName("test");

        when(moveRepositoryMock.findByName("test")).thenReturn(Optional.of(move));

        assertThrows(EntityAlreadyExists.class, () -> moveService.addMove(move));
        verify(loggerMock).info("Checking if move already exists: {}", move);
        verify(loggerMock).warn("Move with name: {} already exists.", move.getName());
    }

    @Test
    public void updateMoveThrowsEntityAlreadyExistsTest() {
        Move move = new Move();
        move.setName("test");

        when(moveRepositoryMock.findByName("test")).thenReturn(Optional.of(move));

        assertThrows(EntityAlreadyExists.class, () -> moveService.updateMove(move, 1L));
        verify(loggerMock).info("Checking if move already exists: {}", move);
        verify(loggerMock).warn("Move with name: {} already exists.", move.getName());
    }

    @Test
    public void getMoveByApiIdWorksCorrectlyTest() {
        Integer apiId = 1001;
        Move expectedMove = new Move();
        expectedMove.setId(1L);
        expectedMove.setApiId(apiId);

        when(moveRepositoryMock.findByApiId(apiId)).thenReturn(Optional.of(expectedMove));

        Move result = moveService.getMoveByApiId(apiId);

        assertNotNull(result);
        assertEquals(apiId, result.getApiId());
        verify(moveRepositoryMock).findByApiId(apiId);
        verify(loggerMock).info("Fetching move by API ID: {}", apiId);
    }

    @Test
    public void getMoveByApiIdThrowsEntityNotFoundTest() {
        Integer apiId = 1001;

        when(moveRepositoryMock.findByApiId(apiId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> moveService.getMoveByApiId(apiId));
        verify(moveRepositoryMock).findByApiId(apiId);
        verify(loggerMock).info("Fetching move by API ID: {}", apiId);
        verify(loggerMock).warn("Move with API ID: {} not found.", apiId);
    }

    @Test
    public void getMoveByNameWorksCorrectlyTest() {
        String name = "test";
        Move expectedMove = new Move();
        expectedMove.setId(1L);
        expectedMove.setName(name);

        when(moveRepositoryMock.findByName(name)).thenReturn(Optional.of(expectedMove));

        Move result = moveService.getMoveByName(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
        verify(moveRepositoryMock).findByName(name);
        verify(loggerMock).info("Fetching move by name: {}", name);
    }

    @Test
    public void getMoveByNameThrowsEntityNotFoundTest() {
        String name = "name";

        when(moveRepositoryMock.findByName(name)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> moveService.getMoveByName(name));
        verify(moveRepositoryMock).findByName(name);
        verify(loggerMock).info("Fetching move by name: {}", name);
        verify(loggerMock).warn("Move with name: {} not found.", name);
    }

    @Test
    public void getMoveByAccuracyWorksCorrectlyTest() {
        Integer accuracy = 90;
        Move move1 = new Move();
        move1.setAccuracy(90);
        Move move2 = new Move();
        move2.setAccuracy(90);
        List<Move> mockMoves = Arrays.asList(move1, move2);

        when(moveRepositoryMock.findByAccuracy(accuracy)).thenReturn(mockMoves);

        List<Move> result = moveService.getMoveByAccuracy(accuracy);

        assertEquals(2, result.size());
        verify(moveRepositoryMock).findByAccuracy(accuracy);
        verify(loggerMock).info("Fetching moves by accuracy: {}", accuracy);
        verify(loggerMock).info("Successfully fetched {} moves with accuracy: {}", mockMoves.size(), accuracy);
    }

    @Test
    public void getMoveByAccuracyThrowsEntityNotFoundTest() {
        Integer accuracy = 50;

        when(moveRepositoryMock.findByAccuracy(accuracy)).thenReturn(List.of());

        assertThrows(EntityNotFound.class, () -> moveService.getMoveByAccuracy(accuracy));

        verify(moveRepositoryMock).findByAccuracy(accuracy);
        verify(loggerMock).info("Fetching moves by accuracy: {}", accuracy);
        verify(loggerMock).warn("No moves found with accuracy: {}", accuracy);
    }

    @Test
    public void getMoveByPowerWorksCorrectlyTest() {
        Integer power = 100;
        Move move1 = new Move();
        move1.setPower(100);
        Move move2 = new Move();
        move2.setPower(100);
        List<Move> mockMoves = Arrays.asList(move1, move2);

        when(moveRepositoryMock.findByPower(power)).thenReturn(mockMoves);

        List<Move> result = moveService.getMoveByPower(power);

        assertEquals(2, result.size());
        verify(moveRepositoryMock).findByPower(power);
        verify(loggerMock).info("Fetching moves by power: {}", power);
        verify(loggerMock).info("Successfully fetched {} moves with power: {}", mockMoves.size(), power);
    }

    @Test
    public void getMoveByPowerThrowsEntityNotFoundTest() {
        Integer power = 10;

        when(moveRepositoryMock.findByPower(power)).thenReturn(List.of());

        assertThrows(EntityNotFound.class, () -> moveService.getMoveByPower(power));

        verify(moveRepositoryMock).findByPower(power);
        verify(loggerMock).info("Fetching moves by power: {}", power);
        verify(loggerMock).warn("No moves found with power: {}", power);
    }

    @Test
    public void getMoveByPpWorksCorrectlyTest() {
        Integer pp = 15;
        Move move1 = new Move();
        move1.setPp(15);
        Move move2 = new Move();
        move2.setPp(15);
        List<Move> mockMoves = Arrays.asList(move1, move2);

        when(moveRepositoryMock.findByPp(pp)).thenReturn(mockMoves);

        List<Move> result = moveService.getMoveByPp(pp);

        assertEquals(2, result.size());
        verify(moveRepositoryMock).findByPp(pp);
        verify(loggerMock).info("Fetching moves by PP: {}", pp);
        verify(loggerMock).info("Successfully fetched {} moves with PP: {}", mockMoves.size(), pp);
    }

    @Test
    public void getMoveByPpThrowsEntityNotFoundTest() {
        Integer pp = 5;

        when(moveRepositoryMock.findByPp(pp)).thenReturn(List.of());

        assertThrows(EntityNotFound.class, () -> moveService.getMoveByPp(pp));

        verify(moveRepositoryMock).findByPp(pp);
        verify(loggerMock).info("Fetching moves by PP: {}", pp);
        verify(loggerMock).warn("No moves found with PP: {}", pp);
    }

    @Test
    public void getAllMovesByTypeIdSuccessTest() {
        Long typeId = 1L;
        Type type = new Type();
        type.setId(1L);
        Move move1 = new Move();
        move1.setType(type);
        Move move2 = new Move();
        move2.setType(type);
        List<Move> mockMoves = Arrays.asList(move1, move2);

        when(moveRepositoryMock.findAllByTypeId(typeId)).thenReturn(mockMoves);

        List<Move> result = moveService.getAllMovesByTypeId(typeId);

        assertEquals(2, result.size());
        verify(moveRepositoryMock).findAllByTypeId(typeId);
        verify(loggerMock).info("Fetching all moves for type with id: {}", typeId);
        verify(loggerMock).info("Successfully fetched {} moves for type with id: {}", mockMoves.size(), typeId);
    }

    @Test
    public void getAllMovesByTypeIdThrowsEntityNotFoundTest() {
        Long typeId = 99L;

        when(moveRepositoryMock.findAllByTypeId(typeId)).thenReturn(List.of());

        assertThrows(EntityNotFound.class, () -> moveService.getAllMovesByTypeId(typeId));

        verify(moveRepositoryMock).findAllByTypeId(typeId);
        verify(loggerMock).info("Fetching all moves for type with id: {}", typeId);
        verify(loggerMock).warn("No moves found for type with id: {}", typeId);
    }
}