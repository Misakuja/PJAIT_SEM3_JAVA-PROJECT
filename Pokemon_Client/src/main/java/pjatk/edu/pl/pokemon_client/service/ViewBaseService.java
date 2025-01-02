package pjatk.edu.pl.pokemon_client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pjatk.edu.pl.pokemon_api.exception.EntityNotFound;

import java.util.List;


@Service
public abstract class ViewBaseService {
    private final RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(ViewBaseService.class);


    protected ViewBaseService(RestClient restclient) {
        this.restClient = restclient;
    }

    protected <T> List<T> getAllEntities(String url) {
        logger.info("Attempting to get all entities");
        List<T> entityList = null;
        try {
            entityList = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            assert entityList != null;
            if (entityList.isEmpty()) {
                throw new EntityNotFound();
            }

        } catch (Exception e) {
            logger.error("Failed to get all entities. {}", String.valueOf(e));
        }
        logger.info("Successfully got all entities");
        return entityList;
    }

    protected void deleteEntity(String url, Long id) {
        logger.info("Attempting to delete entity with ID: {}", id);
        try {
            restClient.delete()
                    .uri(url)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            logger.error("Failed to delete entity. {}", String.valueOf(e));
        }
        logger.info("Successfully deleted entity with ID: {}", id);
    }

    protected <T> void addEntity(String url, T entity) {
        logger.info("Attempting to add entity with values: {}", entity);
        try {
            restClient.post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(entity)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            logger.error("Failed to add entity. {}", String.valueOf(e));
        }
        logger.info("Successfully added entity using values: {}", entity);
    }

    protected <T> void updateEntity(String url, T entity, Long id) {
        logger.info("Attempting to update entity of ID: {} using values: {}", id,  entity);
        try {
            restClient.put()
                    .uri(url)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            logger.error("Failed to patch entity. {}", String.valueOf(e));
        }
        logger.info("Successfully updated entity with ID: {} using values: {}", id, entity);
    }

    protected <T> T getEntityById(String url, Long id, ParameterizedTypeReference<T> typeReference) {
        logger.info("Attempting to get entity with ID: {}", id);
        try {
            T entity = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(typeReference);
            logger.info("Successfully got entity with ID: {}", id);
            return entity;
        } catch (Exception e) {
            logger.error("Failed to get entity by ID. {}", String.valueOf(e));
            throw e;
        }
    }


}
