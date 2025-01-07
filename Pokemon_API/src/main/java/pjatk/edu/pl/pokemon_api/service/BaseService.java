package pjatk.edu.pl.pokemon_api.service;

import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import pjatk.edu.pl.pokemon_data.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.exception.InvalidInput;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {
    private final Logger logger;
    protected final JpaRepository<T, Long> repository;

    protected BaseService(JpaRepository<T, Long> repository, Logger baseServiceLogger) {
        this.repository = repository;
        this.logger = baseServiceLogger;
    }

    protected List<T> getAllEntities() {
        logger.info("Fetching all entities.");
        List<T> entityList = repository.findAll();
        if (entityList.isEmpty()) {
            logger.warn("No entities found.");
            throw new EntityNotFound();
        }
        logger.info("Successfully fetched {} entities.", entityList.size());
        return entityList;
    }

    protected void deleteEntity(Long id) {
        logger.info("Attempting to delete entity with ID: {}", id);

        if (repository.findById(id).isEmpty()) {
            logger.warn("Entity with ID: {} not found for deletion.", id);
            throw new EntityNotFound();
        }
        repository.deleteById(id);
        logger.info("Successfully deleted entity with ID: {}", id);
    }

    protected void addEntity(T entity) {
        logger.info("Attempting to add entity: {}", entity);
        validateEntityInput(entity);
        inputToLowercase(entity);
        repository.save(entity);
        logger.info("Successfully added entity: {}", entity);
    }

    protected void updateEntity(T entity, Long id) {
        logger.info("Attempting to update entity with ID: {}. New values: {}", id, entity);
        Optional<T> entityToUpdate = repository.findById(id);

        if (entityToUpdate.isEmpty()) {
            logger.warn("Entity with ID: {} not found for update.", id);
            throw new EntityNotFound();
        }
        validateEntityInput(entity);
        inputToLowercase(entity);

        try {
            Field idField = entity.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(entity, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("Failed to set ID for entity: {}", e.getMessage());
            throw new RuntimeException("Failed to set ID for entity", e);
        }

        repository.save(entity);
        logger.info("Successfully updated entity with ID: {}", id);

    }

    protected T getEntityById(Long id) {
        logger.info("Fetching entity by ID: {}", id);
        return repository.findById(id).orElseThrow(() -> {
            logger.warn("Entity with ID: {} not found.", id);
            return new EntityNotFound();
        });
    }

    private void validateEntityInput(T entity) {
        logger.info("Validating entity input: {}", entity);
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(entity);
                if (!field.getName().equals("apiId") && !field.getName().equals("id")) {
                    if (value == null || (value instanceof String && ((String) value).isEmpty())) {
                        logger.warn("Invalid input for field: {}. Value: {}", field.getName(), value);
                        throw new InvalidInput();
                    }
                }
            } catch (IllegalAccessException e) {
                logger.error("Error accessing field {}: {}", field.getName(), e.getMessage());
                throw new RuntimeException("Error accessing field " + field.getName() + " whilst validating input.", e);
            }
        }
        logger.info("Entity input validated successfully.");
    }

    private void inputToLowercase(T entity) {
        logger.info("Converting entity fields to lowercase: {}", entity);
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(entity);
                if (value instanceof String) {
                    String lowerCaseValue = ((String) value).toLowerCase();
                    field.set(entity, lowerCaseValue);
                }
            } catch (IllegalAccessException e) {
                logger.error("Error accessing field {} whilst changing input to lowercase. {}", field.getName(), e.getMessage());
                throw new RuntimeException("Error accessing field " + field.getName(), e);
            }
        }
    }
}