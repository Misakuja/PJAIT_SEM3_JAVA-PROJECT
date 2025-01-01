package pjatk.edu.pl.pokemon_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.entity.Type;
import pjatk.edu.pl.pokemon_data.repository.TypeRepository;

import java.util.List;

@Service
public class TypeService extends BaseService<Type> {

    @Autowired
    public TypeService(TypeRepository repository) {
        super(repository);
    }

    public List<Type> getAllTypes() {
        return getAllEntities();
    }

    public void deleteType(Long id) {
        deleteEntity(id);
    }

    public void addType(Type type) {
        addEntity(type);
    }

    public void updateType(Type type, Long id) {
        updateEntity(type, id);
    }

    public Type getTypeById(Long id) {
        return getEntityById(id);
    }
}
