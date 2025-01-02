package pjatk.edu.pl.pokemon_client.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pjatk.edu.pl.pokemon_data.entity.Move;

import java.util.List;

@Service
public class ViewMoveService extends ViewBaseService {
    
    public ViewMoveService(RestClient restClient) {
        super(restClient);
    }

    public List<Move> getAllMoves() {
        return getAllEntities("/move");
    }

    public void deleteMove(Long id) {
        deleteEntity("/move/" + id, id);
    }

    public void addMove(Move move) {
        addEntity("/move/add", move);
    }

    public void updateMove(Move move, Long id) {
        updateEntity("/move/" + id, move, id);
    }

    public Move getMoveById(Long id) {
        return getEntityById("/move/id/" + id, id, new ParameterizedTypeReference<Move>() {});
    }
}
