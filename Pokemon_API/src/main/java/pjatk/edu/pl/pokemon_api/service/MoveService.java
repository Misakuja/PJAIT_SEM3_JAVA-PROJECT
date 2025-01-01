package pjatk.edu.pl.pokemon_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_api.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_api.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.entity.Item;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;
import pjatk.edu.pl.pokemon_data.repository.MoveRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MoveService extends BaseService<Move> {
    private final MoveRepository moveRepository;
    @Autowired
    public MoveService(MoveRepository repository) {
        super(repository);
        this.moveRepository = repository;
    }

    public List<Move> getAllMoves() {
        return getAllEntities();
    }

    public void deleteMove(Long id) {
        deleteEntity(id);
    }

    public void addMove(Move move) {
        checkIfMoveAlreadyExists(move);
        addEntity(move);
    }

    public void updateMove(Move move, Long id) {
        checkIfMoveAlreadyExists(move);
        updateEntity(move, id);
    }

    public Move getMoveById(Long id) {
        return getEntityById(id);
    }

    public Move getMoveByApiId(Integer apiId) {
        return moveRepository.findByApiId(apiId).orElseThrow(EntityNotFound::new);
    }

    public Move getMoveByName(String name) {
        return moveRepository.findByName(name).orElseThrow(EntityNotFound::new);
    }

    public List<Move> getMoveByAccuracy(Integer accuracy) {
        List<Move> moveList = moveRepository.findByAccuracy(accuracy);
        if (moveList.isEmpty()) throw new EntityNotFound();
        else return moveList;
    }

    public List<Move> getMoveByPower(Integer power) {
        List<Move> moveList = moveRepository.findByPower(power);
        if (moveList.isEmpty()) throw new EntityNotFound();
        else return moveList;
    }

    public List<Move> getMoveByPp(Integer pp) {
        List<Move> moveList = moveRepository.findByPp(pp);
        if (moveList.isEmpty()) throw new EntityNotFound();
        else return moveList;
    }

    private void checkIfMoveAlreadyExists(Move move) {
        Optional<Move> existingMove = moveRepository.findByName(move.getName());
        if (existingMove.isPresent()) throw new EntityAlreadyExists();
    }
}
//TODO: get all types