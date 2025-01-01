package pjatk.edu.pl.pokemon_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_data.entity.Ability;
import pjatk.edu.pl.pokemon_data.entity.Item;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Type;
import pjatk.edu.pl.pokemon_data.repository.MoveRepository;

import java.util.List;

@Service
public class MoveService extends BaseService<Move> {

    @Autowired
    public MoveService(MoveRepository repository) {
        super(repository);
    }

    public List<Move> getAllMoves() {
        return getAllEntities();
    }

    public void deleteMove(Long id) {
        deleteEntity(id);
    }

    public void addMove(Move move) {
        addEntity(move);
    }

    public void updateMove(Move move, Long id) {
        updateEntity(move, id);
    }

    public Move getMoveById(Long id) {
        return getEntityById(id);
    }
}
