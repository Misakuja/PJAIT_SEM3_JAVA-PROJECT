package pjatk.edu.pl.pokemon_api.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pjatk.edu.pl.pokemon_api.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_api.exception.InvalidInput;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {
    protected final JpaRepository<T, Long> repository;

    protected BaseService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    protected List<T> getAllEntities() {
        List<T> entityList = repository.findAll();
        if (entityList.isEmpty()) throw new EntityNotFound();
        return entityList;
    }

    protected void deleteEntity(Long id) {
        if (repository.findById(id).isEmpty()) throw new EntityNotFound();
        repository.deleteById(id);
    }

    protected void addEntity(T entity) {
        validateEntityInput(entity);
        inputToLowercase(entity);
        repository.save(entity);
    }

    protected void updateEntity(T entity, Long id) {
        Optional<T> entityToUpdate = repository.findById(id);

        if (entityToUpdate.isEmpty()) throw new EntityNotFound();
        validateEntityInput(entity);

        inputToLowercase(entity);

        try {
            Field idField = entity.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(entity, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set ID for entity", e);
        }

        repository.save(entity);
    }

    protected T getEntityById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFound::new);
    }

    protected List<T> getEntityByField(String fieldName, Object value) {
        List<T> entities = repository.findAll();
        List<T> foundEntities = new ArrayList<>();
        for (T entity : entities) {
            try {
                Field field = entity.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object fieldValue = field.get(entity);
                if (fieldValue != null && fieldValue.equals(value)) {
                    foundEntities.add(entity);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Error accessing field " + fieldName, e);
            }
        }
        if (foundEntities.isEmpty()) throw new EntityNotFound();
        else return foundEntities;
    }

    private void validateEntityInput(T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(entity);
                if (!field.getName().equals("apiId") && !field.getName().equals("id")) {
                    if (value == null || (value instanceof String && ((String) value).isEmpty())) {
                        throw new InvalidInput();
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field " + field.getName(), e);
            }
        }
    }

    private void inputToLowercase(T entity) {
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
                throw new RuntimeException("Error accessing field " + field.getName(), e);
            }
        }
    }

}