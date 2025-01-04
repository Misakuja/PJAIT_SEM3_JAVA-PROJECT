package pjatk.edu.pl.pokemon_client.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pjatk.edu.pl.pokemon_data.entity.Ability;

import java.util.List;

@Service
public class ViewAbilityService extends ViewBaseService {

    public ViewAbilityService(RestClient restClient) {
        super(restClient);
    }

    public List<Ability> getAllAbilities() {
        return getAllEntities("/ability");
    }

    public void deleteAbility(Long id) {
        deleteEntity("/ability/" + id, id);
    }

    public void addAbility(Ability ability) {
        addEntity("/ability", ability);
    }

    public void updateAbility(Ability ability, Long id) {
        updateEntity("/ability/" + id, ability, id);
    }

    public Ability getAbilityById(Long id) {
        return getEntityByField("/ability/apiId/" + id, id, new ParameterizedTypeReference<>() {});
    }

    public Ability getAbilityByApiId(Integer apiId) {
        return getEntityByField("/ability/apiId/" + apiId, apiId, new ParameterizedTypeReference<>() {});
    }

    public Ability getAbilityByName(String name) {
        return getEntityByField("/ability/name/" + name, name, new ParameterizedTypeReference<>() {});
    }

}