package pjatk.edu.pl.pokemon_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_data.entity.Type;
import pjatk.edu.pl.pokemon_data.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_data.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.repository.MoveRepository;
import pjatk.edu.pl.pokemon_data.repository.TypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService extends BaseService<Type> {
    private static final Logger logger = LoggerFactory.getLogger(TypeService.class);
    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository repository, MoveRepository moveRepository) {
        super(repository);
        this.typeRepository = repository;
    }

    public List<Type> getAllTypes() {
        return getAllEntities();
    }

    public void deleteType(Long id) {
        deleteEntity(id);
    }

    public void addType(Type type) {
        checkIfTypeAlreadyExists(type);
        addEntity(type);
    }

    public void updateType(Type type, Long id) {
        checkIfTypeAlreadyExists(type);
        updateEntity(type, id);
    }

    public Type getTypeById(Long id) {
        return getEntityById(id);
    }

    public Type getTypeByApiId(Integer apiId) {
        logger.info("Fetching type by API ID: {}", apiId);
        return typeRepository.findByApiId(apiId).orElseThrow(() -> {
            logger.warn("Type with API ID: {} not found.", apiId);
            return new EntityNotFound();
        });
    }

    public Type getTypeByName(String name) {
        logger.info("Fetching type by name: {}", name);
        return typeRepository.findByName(name).orElseThrow(() -> {
            logger.warn("Type with name: {} not found.", name);
            return new EntityNotFound();
        });
    }

    private void checkIfTypeAlreadyExists(Type type) {
        logger.info("Checking if type already exists: {}", type);
        Optional<Type> existingType = typeRepository.findByName(type.getName());
        if (existingType.isPresent()) {
            logger.warn("Type with name: {} already exists.", type.getName());
            throw new EntityAlreadyExists();
        }
        logger.info("Type with name: {} does not exist. Proceeding to add.", type.getName());
    }
}
