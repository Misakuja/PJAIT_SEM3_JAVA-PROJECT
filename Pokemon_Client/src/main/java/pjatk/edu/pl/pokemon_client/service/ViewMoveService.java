package pjatk.edu.pl.pokemon_client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pjatk.edu.pl.pokemon_data.entity.Move;

import java.util.List;

@Service
public class ViewMoveService extends ViewBaseService {
    private final RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(ViewMoveService.class);

    public ViewMoveService(RestClient restClient) {
        super(restClient);
        this.restClient = restClient;
    }

    public List<Move> getAllMoves() {
        return getAllEntities("/move");
    }

    public List<Move> getAllMovesByTypeId(Long typeId) {
        String logContext = "type ID: " + typeId;
        return getAllEntitiesOfRelationById("/move/get/type/" + typeId, new ParameterizedTypeReference<>() {}, logContext);
    }

    public void deleteMove(Long id) {
        deleteEntity("/move/" + id, id);
    }

    public void addMove(Move move) {
        addEntity("/move/", move);
    }

    public void updateMove(Move move, Long id) {
        updateEntity("/move/" + id, move, id);
    }

    public Move getMoveById(Long id) {
        return getEntityByField("/move/apiId/" + id, id, new ParameterizedTypeReference<>() {});
    }

    public Move getMoveByApiId(Integer apiId) {
        return getEntityByField("/move/apiId/" + apiId, apiId, new ParameterizedTypeReference<>() {});
    }

    public Move getMoveByName(String name) {
        return getEntityByField("/move/name/" + name, name, new ParameterizedTypeReference<>() {});
    }

    public List<Move> getMoveByAccuracy(Integer accuracy) {
        return getEntityListByField("/move/accuracy/" + accuracy, accuracy);
    }

    public List<Move> getMoveByPower(Integer power) {
        return getEntityListByField("/move/power/" + power, power);
    }

    public List<Move> getMoveByPp(Integer pp) {
        return getEntityListByField("/move/pp/" + pp, pp);
    }
}
