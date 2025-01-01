package pjatk.edu.pl.pokemon_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_data.entity.Ability;
import pjatk.edu.pl.pokemon_data.entity.Item;
import pjatk.edu.pl.pokemon_data.entity.Type;
import pjatk.edu.pl.pokemon_data.repository.AbilityRepository;

import java.util.List;

@Service
public class AbilityService extends BaseService<Ability> {

    @Autowired
    public AbilityService(AbilityRepository repository) {
        super(repository);
    }

    public List<Ability> getAllAbilities() {
        return getAllEntities();
    }

    public void deleteAbility(Long id) {
        deleteEntity(id);
    }

    public void addAbility(Ability ability) {
        addEntity(ability);
    }

    public void updateAbility(Ability ability, Long id) {
        updateEntity(ability, id);
    }

    public Ability getAbilityById(Long id) {
        return getEntityById(id);
    }
}
