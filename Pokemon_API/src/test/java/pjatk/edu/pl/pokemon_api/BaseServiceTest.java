package pjatk.edu.pl.pokemon_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import pjatk.edu.pl.pokemon_api.service.BaseService;
import pjatk.edu.pl.pokemon_data.entity.Item;
import pjatk.edu.pl.pokemon_data.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.exception.InvalidInput;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BaseServiceTest {

    @Mock
    private JpaRepository<Object, Long> repositoryMock;

    @Mock
    private Logger loggerMock;

    private static class BaseServiceTestSubclass extends BaseService<Object> {

        public BaseServiceTestSubclass(JpaRepository<Object, Long> repository, Logger logger) {
            super(repository, logger);
        }

        public List<Object> getAllEntitiesForTest() {
            return getAllEntities();
        }

        public void deleteEntityForTest(Long id) {
            deleteEntity(id);
        }

        public void addEntityForTest(Object entity) {
            addEntity(entity);
        }

        public void updateEntityForTest(Object entity, Long id) {
            updateEntity(entity, id);
        }

        public Object getEntityByIdForTest(Long id) {
            return getEntityById(id);
        }
    }

    private BaseServiceTestSubclass baseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        baseService = new BaseServiceTestSubclass(repositoryMock, loggerMock);
    }

    @Test
    public void getAllEntitiesWorksCorrectlyTest() {
        List<Object> mockObjects = List.of(new Object(), new Object());

        when(repositoryMock.findAll()).thenReturn(mockObjects);

        List<Object> result = baseService.getAllEntitiesForTest();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(loggerMock).info("Fetching all entities.");
        verify(loggerMock).info("Successfully fetched {} entities.", mockObjects.size());
    }

    @Test
    public void getAllEntitiesThrowsEntityNotFoundTest() {
        when(repositoryMock.findAll()).thenReturn(List.of());

        assertThrows(EntityNotFound.class, () -> baseService.getAllEntitiesForTest());
        verify(loggerMock).info("Fetching all entities.");
        verify(loggerMock).warn("No entities found.");
    }

    @Test
    public void deleteEntityWorksCorrectlyTest() {
        Long testId = 1L;
        Object mockEntity = new Object();

        when(repositoryMock.findById(testId)).thenReturn(Optional.of(mockEntity));

        baseService.deleteEntityForTest(testId);

        verify(repositoryMock).deleteById(testId);
        verify(loggerMock).info("Attempting to delete entity with ID: {}", testId);
        verify(loggerMock).info("Successfully deleted entity with ID: {}", testId);
    }

    @Test
    public void deleteEntityThrowsEntityNotFoundTest() {
        Long entityId = 1L;
        when(repositoryMock.findById(entityId)).thenReturn(Optional.empty());

        EntityNotFound exception = assertThrows(EntityNotFound.class, () -> baseService.deleteEntityForTest(entityId));
        assertNotNull(exception);

        verify(loggerMock).info("Attempting to delete entity with ID: {}", entityId);
        verify(loggerMock).warn("Entity with ID: {} not found for deletion.", entityId);
    }

    @Test
    public void addEntityWorksCorrectlyTest() {
        Object mockEntity = new Object();

        baseService.addEntityForTest(mockEntity);

        verify(repositoryMock).save(mockEntity);
        verify(loggerMock).info("Attempting to add entity: {}", mockEntity);
        verify(loggerMock).info("Successfully added entity: {}", mockEntity);
    }

    @Test
    public void addEntityThrowsInvalidInputTest() {
        Item item = new Item();
        item.setName("");

        assertThrows(InvalidInput.class, () -> baseService.addEntityForTest(item));
        verify(loggerMock).info("Attempting to add entity: {}", item);
        verify(loggerMock).warn(eq("Invalid input for field: {}. Value: {}"), any(), any());
    }

    @Test
    public void updateEntityWorksCorrectlyTest() {
        Item item = new Item();
        item.setName("name");
        item.setApiId(23);
        Long testId = 1L;

        when(repositoryMock.findById(testId)).thenReturn(Optional.of(item));

        baseService.updateEntityForTest(item, testId);

        verify(repositoryMock).save(item);
        verify(loggerMock).info("Attempting to update entity with ID: {}. New values: {}", testId, item);
        verify(loggerMock).info("Successfully updated entity with ID: {}", testId);
    }

    @Test
    public void updateThrowsInvalidInputTest() {
        Item item = new Item();
        Long testId = 1L;

        when(repositoryMock.findById(testId)).thenReturn(Optional.of(item));

        assertThrows(InvalidInput.class, () ->  baseService.updateEntityForTest(item, testId));
        verify(loggerMock).info("Attempting to update entity with ID: {}. New values: {}", testId, item);
        verify(loggerMock).warn(eq("Invalid input for field: {}. Value: {}"),any(), any());
    }

    @Test
    public void updateThrowsEntityNotFoundTest() {
        Item item = new Item();
        Long testId = 1L;

        when(repositoryMock.findById(testId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () ->  baseService.updateEntityForTest(item, testId));
        verify(loggerMock).info("Attempting to update entity with ID: {}. New values: {}", testId, item);
        verify(loggerMock).warn("Entity with ID: {} not found for update.", testId);
    }

    @Test
    public void getEntityByIdWorksCorrectlyTest() {
        Object object = new Object();
        Long testId = 1L;

        when(repositoryMock.findById(testId)).thenReturn(Optional.of(object));

        Object result = baseService.getEntityByIdForTest(testId);
        assertNotNull(result);
        verify(loggerMock).info("Fetching entity by ID: {}", testId);
    }

    @Test
    public void getEntityByIdThrowsEntityNotFoundTest() {
        Long testId = 1L;

        when(repositoryMock.findById(testId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () ->  baseService.getEntityByIdForTest(testId));
        verify(loggerMock).info("Fetching entity by ID: {}", testId);
    }

}