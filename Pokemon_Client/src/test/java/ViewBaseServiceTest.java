import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import pjatk.edu.pl.pokemon_client.service.ViewBaseService;
import pjatk.edu.pl.pokemon_data.entity.Item;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Type;
import pjatk.edu.pl.pokemon_data.exception.EntityNotFound;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ViewBaseServiceTest extends ViewBaseService {
    private static final Logger loggerMock = mock(Logger.class);
    private static final RestClient restClientMock = mock(RestClient.class);

    public ViewBaseServiceTest() {
        super(restClientMock, loggerMock);
    }

    @BeforeEach
    public void resetMocks() {
        reset(loggerMock, restClientMock);
    }

    @Test
    public void getAllEntitiesWorksCorrectlyTest() {
        String testUrl = "/item";

        RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpecMock = mock(RestClient.ResponseSpec.class);

        List<Item> mockItems = List.of(new Item(), new Item());

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq(testUrl))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.body(any(ParameterizedTypeReference.class))).thenReturn(mockItems);

        List<Item> result = this.getAllEntities(testUrl);

        verify(loggerMock).info("Attempting to get all entities");
        verify(loggerMock).info("Successfully got all entities");
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void getAllEntitiesThrowsExceptionTest() {
        String testUrl = "/item";

        RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq(testUrl))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenThrow(new RuntimeException("Test exception"));

        doNothing().when(loggerMock).error(anyString(), Optional.ofNullable(any()));

        assertThrows(RuntimeException.class, () -> this.getAllEntities(testUrl));

        verify(loggerMock).info("Attempting to get all entities");
        verify(loggerMock).error(anyString(), Optional.ofNullable(any()));
    }

    @Test
    public void getAllEntitiesThrowsEntityNotFoundExceptionTest() {
        String testUrl = "/item";

        RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpecMock = mock(RestClient.ResponseSpec.class);

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq(testUrl))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.body(any(ParameterizedTypeReference.class))).thenReturn(List.of());

        doNothing().when(loggerMock).info(anyString());
        doNothing().when(loggerMock).error(anyString(), Optional.ofNullable(any()));

        assertThrows(EntityNotFound.class, () -> this.getAllEntities(testUrl));
        verify(loggerMock).info("Attempting to get all entities");
        verify(loggerMock).error(contains("Failed to get all entities."), Optional.ofNullable(any()));
    }

    @Test
    public void getAllEntitiesOfRelationsWorksCorrectlyTest() {
        String testUrl = "/move/get/type/";

        RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpecMock = mock(RestClient.ResponseSpec.class);

        List<Type> mockItems = List.of(new Type(), new Type());

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq(testUrl))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.body(any(ParameterizedTypeReference.class))).thenReturn(mockItems);

        List<Type> result = this.getAllEntitiesOfRelationById(testUrl, mock(ParameterizedTypeReference.class), "");

        verify(loggerMock).info("Fetching all entities for {}", "");
        verify(loggerMock).info("Successfully fetched {} entities for {}", result.size(), "");
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void getAllEntitiesOfRelationsThrowsExceptionTest() {
        String testUrl = "/move/get/type";
        String param = "";

        RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq(testUrl))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenThrow(new RuntimeException(""));

        doNothing().when(loggerMock).info(anyString(), Optional.ofNullable(any()));
        doNothing().when(loggerMock).error(anyString(), Optional.ofNullable(any()), any());

        assertThrows(RuntimeException.class, () -> this.getAllEntitiesOfRelationById(testUrl, mock(ParameterizedTypeReference.class), param));
        verify(loggerMock).info("Fetching all entities for {}", param);
        verify(loggerMock).error(anyString(), Optional.ofNullable(any()), any());
    }

    @Test
    public void getAllEntitiesOfRelationsThrowsEntityNotFoundExceptionTest() {
        String testUrl = "/move/get/type";
        String param = "";

        RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpecMock = mock(RestClient.ResponseSpec.class);

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq(testUrl))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.body(any(ParameterizedTypeReference.class))).thenReturn(List.of());

        doNothing().when(loggerMock).info(anyString(), Optional.ofNullable(any()));
        doNothing().when(loggerMock).error(anyString(), Optional.ofNullable(any()), any());

        assertThrows(EntityNotFound.class, () -> this.getAllEntitiesOfRelationById(testUrl, mock(ParameterizedTypeReference.class), param));
        verify(loggerMock).info(eq("Fetching all entities for {}"), eq(param));
        verify(loggerMock).error(anyString(), Optional.ofNullable(any()), any());
    }

    @Test
    public void deleteEntityWorksCorrectlyTest() {
        String testUrl = "/item";
        Long testId = 2L;

        RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpecMock = mock(RestClient.ResponseSpec.class);

        when(restClientMock.delete()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq(testUrl))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(mock(RestClient.ResponseSpec.class));

        this.deleteEntity(testUrl, testId);

        verify(loggerMock).info("Attempting to delete entity with ID: {}", testId);
        verify(loggerMock).info("Successfully deleted entity with ID: {}", testId);
        verify(restClientMock).delete();
        verify(requestHeadersUriSpecMock).uri(eq(testUrl));
        verify(requestHeadersSpecMock).retrieve();
    }

    @Test
    public void deleteEntityThrowsExceptionTest() {
        String testUrl = "/item";
        Long testId = 1L;

        RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);

        when(restClientMock.delete()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq(testUrl))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenThrow(new RuntimeException(""));

        doNothing().when(loggerMock).error(anyString(), Optional.ofNullable(any()));

        this.deleteEntity(testUrl, testId);

        verify(loggerMock).info("Attempting to delete entity with ID: {}", testId);
        verify(loggerMock).error(anyString(), Optional.ofNullable(any()));
    }

    @Test
    public void addEntityWorksCorrectlyTest() {
        String testUrl = "/item";
        Item testItem = new Item();

        RestClient.RequestBodyUriSpec postRequestMock = mock(RestClient.RequestBodyUriSpec.class);
        RestClient.RequestBodySpec bodySpecMock = mock(RestClient.RequestBodySpec.class);
        RestClient.ResponseSpec responseSpecMock = mock(RestClient.ResponseSpec.class);

        when(restClientMock.post()).thenReturn(postRequestMock);
        when(postRequestMock.uri(eq(testUrl))).thenReturn(bodySpecMock);
        when(bodySpecMock.contentType(eq(MediaType.APPLICATION_JSON))).thenReturn(bodySpecMock);
        when(bodySpecMock.body(eq(testItem))).thenReturn(bodySpecMock);
        when(bodySpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toBodilessEntity()).thenReturn(null);

        this.addEntity(testUrl, testItem);

        verify(loggerMock).info("Successfully added entity using values: {}", testItem);
        verify(loggerMock).info("Attempting to add entity with values: {}", testItem);

        verify(restClientMock).post();
        verify(postRequestMock).uri(eq(testUrl));
        verify(bodySpecMock).retrieve();
        verify(bodySpecMock).body(eq(testItem));
    }

    @Test
    public void addEntityThrowsExceptionTest() {
        String testUrl = "/item";
        Item testItem = new Item();

        RestClient.RequestBodyUriSpec postRequestMock = mock(RestClient.RequestBodyUriSpec.class);
        RestClient.RequestBodySpec bodySpecMock = mock(RestClient.RequestBodySpec.class);

        when(restClientMock.post()).thenReturn(postRequestMock);
        when(postRequestMock.uri(eq(testUrl))).thenReturn(bodySpecMock);
        when(bodySpecMock.contentType(eq(MediaType.APPLICATION_JSON))).thenReturn(bodySpecMock);
        when(bodySpecMock.body(eq(testItem))).thenReturn(bodySpecMock);
        when(bodySpecMock.retrieve()).thenThrow(new RuntimeException(""));

        doNothing().when(loggerMock).error(anyString(), Optional.ofNullable(any()));

        this.addEntity(testUrl, testItem);

        verify(loggerMock).info("Attempting to add entity with values: {}", testItem);
        verify(loggerMock).error(anyString(), Optional.ofNullable(any()));
    }

    @Test
    public void updateEntityWorksCorrectlyTest() {
        String testUrl = "/item";
        Item testItem = new Item();
        Long testId = 1L;

        RestClient.RequestBodyUriSpec postRequestMock = mock(RestClient.RequestBodyUriSpec.class);
        RestClient.RequestBodySpec bodySpecMock = mock(RestClient.RequestBodySpec.class);
        RestClient.ResponseSpec responseSpecMock = mock(RestClient.ResponseSpec.class);

        when(restClientMock.patch()).thenReturn(postRequestMock);
        when(postRequestMock.uri(eq(testUrl))).thenReturn(bodySpecMock);
        when(bodySpecMock.contentType(eq(MediaType.APPLICATION_JSON))).thenReturn(bodySpecMock);
        when(bodySpecMock.body(eq(testItem))).thenReturn(bodySpecMock);
        when(bodySpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toBodilessEntity()).thenReturn(null);

        this.updateEntity(testUrl, testItem, testId);

        verify(loggerMock).info("Attempting to update entity of ID: {} using values: {}", testId, testItem);
        verify(loggerMock).info("Successfully updated entity with ID: {} using values: {}", testId, testItem);

        verify(restClientMock).patch();
        verify(postRequestMock).uri(eq(testUrl));
        verify(bodySpecMock).retrieve();
        verify(bodySpecMock).body(eq(testItem));
    }

    @Test
    public void updateEntityThrowsExceptionTest() {
        String testUrl = "/item";
        Item testItem = new Item();
        Long testId = 1L;


        RestClient.RequestBodyUriSpec postRequestMock = mock(RestClient.RequestBodyUriSpec.class);
        RestClient.RequestBodySpec bodySpecMock = mock(RestClient.RequestBodySpec.class);
        RestClient.ResponseSpec responseSpecMock = mock(RestClient.ResponseSpec.class);

        when(restClientMock.patch()).thenReturn(postRequestMock);
        when(postRequestMock.uri(eq(testUrl))).thenReturn(bodySpecMock);
        when(bodySpecMock.contentType(eq(MediaType.APPLICATION_JSON))).thenReturn(bodySpecMock);
        when(bodySpecMock.body(eq(testItem))).thenReturn(bodySpecMock);
        when(bodySpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.toBodilessEntity()).thenThrow(RuntimeException.class);

        doNothing().when(loggerMock).warn(anyString(), Optional.ofNullable(any()));

        this.updateEntity(testUrl, testItem, testId);

        verify(loggerMock).info("Attempting to update entity of ID: {} using values: {}", testId, testItem);
        verify(loggerMock).warn(anyString(), Optional.ofNullable(any()));
    }

    @Test
    public void getEntityByFieldWorksCorrectlyTest() {
        String testUrl = "/item";
        String field = "testField";
        Item mockItem = new Item();

        RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpecMock = mock(RestClient.ResponseSpec.class);

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq(testUrl))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.body(any(ParameterizedTypeReference.class))).thenReturn(mockItem);

        Object result = this.getEntityByField(testUrl, field, mock(ParameterizedTypeReference.class));

        verify(loggerMock).info("Attempting to get entity by field. Using value: {}", field);
        verify(loggerMock).info("Successfully got entity by field, using value {}.", field);
        assertNotNull(result);
        assertEquals(mockItem, result);
    }

    @Test
    public void getEntityByFieldThrowsExceptionTest() {
        String testUrl = "/item";
        String field = "testField";

        RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpecMock = mock(RestClient.ResponseSpec.class);

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq(testUrl))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.body(any(ParameterizedTypeReference.class))).thenThrow(new RuntimeException("Test error"));

        doNothing().when(loggerMock).error(anyString(), Optional.ofNullable(any()));

        Object result = this.getEntityByField(testUrl, field, mock(ParameterizedTypeReference.class));

        verify(loggerMock).info("Attempting to get entity by field. Using value: {}", field);
        verify(loggerMock).error(anyString(), any(), any());
        assertNull(result);
    }

    @Test
    public void getEntityListByFieldWorksCorrectlyTest() {
        String testUrl = "/move/accuracy/";
        String field = "testField";
        Move mockMove = new Move();

        RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpecMock = mock(RestClient.ResponseSpec.class);

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq(testUrl))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.body(any(ParameterizedTypeReference.class))).thenReturn(List.of(mockMove));

        List<Object> result = this.getEntityListByField(testUrl, field);

        verify(loggerMock).info("Attempting to get all entities by field. Using value: {}", field);
        verify(loggerMock).info("Successfully got all entities by field, using value {}.", field);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockMove, result.getFirst());
    }

    @Test
    public void getEntityListByFieldThrowsExceptionTest() {
        String testUrl = "/item";
        String field = "testField";

        RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpecMock = mock(RestClient.ResponseSpec.class);

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq(testUrl))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.body(any(ParameterizedTypeReference.class))).thenThrow(new RuntimeException("Test error"));

        assertThrows(RuntimeException.class, () -> this.getEntityListByField(testUrl, field));
        verify(loggerMock).info("Attempting to get all entities by field. Using value: {}", field);
        verify(loggerMock).error(anyString(), any(), any());
    }

    @Test
    public void getEntityListByFieldThrowsEntityNotFoundExceptionTest() {
        String testUrl = "/item";
        String field = "testField";

        RestClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(RestClient.RequestHeadersUriSpec.class);
        RestClient.RequestHeadersSpec requestHeadersSpecMock = mock(RestClient.RequestHeadersSpec.class);
        RestClient.ResponseSpec responseSpecMock = mock(RestClient.ResponseSpec.class);

        when(restClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(eq(testUrl))).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.body(any(ParameterizedTypeReference.class))).thenReturn(List.of());

        doNothing().when(loggerMock).info(anyString(), Optional.ofNullable(any()));
        doNothing().when(loggerMock).error(anyString(), Optional.ofNullable(any()), any());

        assertThrows(EntityNotFound.class, () -> {this.getEntityListByField(testUrl, field);});
        verify(loggerMock).info("Attempting to get all entities by field. Using value: {}", field);
        verify(loggerMock).error(anyString(), any(), any());
    }
}