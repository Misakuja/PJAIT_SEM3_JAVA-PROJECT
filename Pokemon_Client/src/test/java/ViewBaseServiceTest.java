
import org.junit.jupiter.api.Test;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import pjatk.edu.pl.pokemon_client.service.ViewBaseService;
import pjatk.edu.pl.pokemon_data.entity.Item;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ViewBaseServiceTest extends ViewBaseService {
    private static final RestClient restClientMock = mock(RestClient.class);

    protected ViewBaseServiceTest() {
        super(restClientMock);
    }

    @Test
    public void getAllEntitiesWorksCorrectlyTest() {
        String testUrl = "/item";

        List<Item> mockItems = List.of(new Item(), new Item());


        when(restClientMock.get()
                .uri(testUrl)
                .retrieve()
                .body(mock(ParameterizedTypeReference.class)))
                .thenReturn(mockItems);

        List<Item> result = this.getAllEntities(testUrl);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    //    protected <T> List<T> getAllEntities(String url) {
    //        logger.info("Attempting to get all entities");
    //        List<T> entityList = null;
    //        try {
    //            entityList = restClient.get()
    //                    .uri(url)
    //                    .retrieve()
    //                    .body(new ParameterizedTypeReference<>() {
    //                    });
    //
    //            if (entityList != null && entityList.isEmpty()) {
    //                throw new EntityNotFound();
    //            }
    //
    //        } catch (Exception e) {
    //            logger.error("Failed to get all entities. {}", String.valueOf(e));
    //        }
    //        logger.info("Successfully got all entities");
    //        return entityList;
    //    }

}
