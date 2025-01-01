package pjatk.edu.pl.pokemon_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_api.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_api.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.entity.Type;
import pjatk.edu.pl.pokemon_data.repository.TypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService extends BaseService<Type> {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository repository) {
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
        return typeRepository.findByApiId(apiId).orElseThrow(EntityNotFound::new);
    }

    public Type getTypeByName(String name) {
        return typeRepository.findByName(name).orElseThrow(EntityNotFound::new);
    }

    private void checkIfTypeAlreadyExists(Type type) {
        Optional<Type> existingPokemon = typeRepository.findByName(type.getName());
        if (existingPokemon.isPresent()) throw new EntityAlreadyExists();
    }
}
