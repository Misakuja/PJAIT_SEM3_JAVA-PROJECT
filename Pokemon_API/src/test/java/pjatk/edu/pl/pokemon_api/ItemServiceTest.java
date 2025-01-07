package pjatk.edu.pl.pokemon_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import pjatk.edu.pl.pokemon_api.service.ItemService;
import pjatk.edu.pl.pokemon_data.entity.Item;
import pjatk.edu.pl.pokemon_data.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_data.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.repository.ItemRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepositoryMock;

    @Mock
    private Logger loggerMock;

    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemService(itemRepositoryMock, loggerMock);
    }

    @Test
    public void addItemThrowsEntityAlreadyExistsTest() {
        Item item = new Item();
        item.setName("test");

        when(itemRepositoryMock.findByName("test")).thenReturn(Optional.of(item));

        assertThrows(EntityAlreadyExists.class, () -> itemService.addItem(item));
        verify(loggerMock).info("Checking if item already exists: {}", item);
        verify(loggerMock).warn("Item with name: {} already exists.", item.getName());
    }

    @Test
    public void updateItemThrowsEntityAlreadyExistsTest() {
        Item item = new Item();
        item.setName("test");

        when(itemRepositoryMock.findByName("test")).thenReturn(Optional.of(item));

        assertThrows(EntityAlreadyExists.class, () -> itemService.updateItem(item, 1L));
        verify(loggerMock).info("Checking if item already exists: {}", item);
        verify(loggerMock).warn("Item with name: {} already exists.", item.getName());
    }

    @Test
    public void getItemByApiIdWorksCorrectlyTest() {
        Integer apiId = 1001;
        Item expectedItem = new Item();
        expectedItem.setId(1L);
        expectedItem.setApiId(apiId);

        when(itemRepositoryMock.findByApiId(apiId)).thenReturn(Optional.of(expectedItem));

        Item result = itemService.getItemByApiId(apiId);

        assertNotNull(result);
        assertEquals(apiId, result.getApiId());
        verify(itemRepositoryMock).findByApiId(apiId);
        verify(loggerMock).info("Fetching item by API ID: {}", apiId);
    }

    @Test
    public void getItemByApiIdThrowsEntityNotFoundTest() {
        Integer apiId = 1001;

        when(itemRepositoryMock.findByApiId(apiId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> itemService.getItemByApiId(apiId));
        verify(itemRepositoryMock).findByApiId(apiId);
        verify(loggerMock).info("Fetching item by API ID: {}", apiId);
        verify(loggerMock).warn("Item with API ID: {} not found.", apiId);
    }

    @Test
    public void getItemByNameWorksCorrectlyTest() {
        String name = "test";
        Item expectedItem = new Item();
        expectedItem.setId(1L);
        expectedItem.setName(name);

        when(itemRepositoryMock.findByName(name)).thenReturn(Optional.of(expectedItem));

        Item result = itemService.getItemByName(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
        verify(itemRepositoryMock).findByName(name);
        verify(loggerMock).info("Fetching item by name: {}", name);
    }

    @Test
    public void getItemByNameThrowsEntityNotFoundTest() {
        String name = "name";

        when(itemRepositoryMock.findByName(name)).thenReturn(Optional.empty());

        assertThrows(EntityNotFound.class, () -> itemService.getItemByName(name));
        verify(itemRepositoryMock).findByName(name);
        verify(loggerMock).info("Fetching item by name: {}", name);
        verify(loggerMock).warn("Item with name: {} not found.", name);
    }
}