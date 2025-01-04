package pjatk.edu.pl.pokemon_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_data.entity.Ability;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_data.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.repository.AbilityRepository;
import pjatk.edu.pl.pokemon_data.repository.PokemonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AbilityService extends BaseService<Ability> {
    private static final Logger logger = LoggerFactory.getLogger(AbilityService.class);
    private final AbilityRepository abilityRepository;
    private final PokemonRepository pokemonRepository;

    @Autowired
    public AbilityService(AbilityRepository abilityRepository, PokemonRepository pokemonRepository) {
        super(abilityRepository);
        this.abilityRepository = abilityRepository;
        this.pokemonRepository = pokemonRepository;
    }

    public List<Ability> getAllAbilities() {
        return getAllEntities();
    }

    public void deleteAbility(Long id) {
        for (Pokemon pokemon : pokemonRepository.findAllByAbilityId(id)) {
            pokemon.setAbilities(null);
            pokemonRepository.save(pokemon);
        }

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
        logger.info("Fetching ability by API ID: {}", apiId);
        return abilityRepository.findByApiId(apiId).orElseThrow(() -> {
            logger.warn("Ability with API ID: {} not found.", apiId);
            return new EntityNotFound();
        });
    }

    public Ability getAbilityByName(String name) {
        logger.info("Fetching ability by name: {}", name);
        return abilityRepository.findByName(name).orElseThrow(() -> {
            logger.warn("Ability with name: {} not found.", name);
            return new EntityNotFound();
        });
    }

    private void checkIfAbilityAlreadyExists(Ability ability) {
        logger.info("Checking if ability already exists: {}", ability);
        Optional<Ability> existingAbility = abilityRepository.findByName(ability.getName());
        if (existingAbility.isPresent()) {
            logger.warn("Ability with name: {} already exists.", ability.getName());
            throw new EntityAlreadyExists();
        }
        logger.info("Ability with name: {} does not exist. Proceeding to add.", ability.getName());
    }
}
