package pjatk.edu.pl.pokemon_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_api.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_api.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.entity.Ability;
import pjatk.edu.pl.pokemon_data.repository.AbilityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AbilityService extends BaseService<Ability> {
    private final AbilityRepository abilityRepository;

    @Autowired
    public AbilityService(AbilityRepository repository) {
        super(repository);
        this.abilityRepository = repository;
    }

    public List<Ability> getAllAbilities() {
        return getAllEntities();
    }

    public void deleteAbility(Long id) {
        deleteEntity(id);
    }

    public void addAbility(Ability ability) {
        checkIfAbilityAlreadyExists(ability);
        addEntity(ability);
    }

    public void updateAbility(Ability ability, Long id) {
        checkIfAbilityAlreadyExists(ability);
        updateEntity(ability, id);
    }

    public Ability getAbilityById(Long id) {
        return getEntityById(id);
    }

    public Ability getAbilityByApiId(Integer apiId) {
        return abilityRepository.findByApiId(apiId).orElseThrow(EntityNotFound::new);
    }

    public Ability getAbilityByName(String name) {
        return abilityRepository.findByName(name).orElseThrow(EntityNotFound::new);
    }

    private void checkIfAbilityAlreadyExists(Ability ability) {
        Optional<Ability> existingAbility = abilityRepository.findByName(ability.getName());
        if (existingAbility.isPresent()) throw new EntityAlreadyExists();
    }
}
