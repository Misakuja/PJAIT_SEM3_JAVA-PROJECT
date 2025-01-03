package pjatk.edu.pl.pokemon_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.edu.pl.pokemon_data.entity.Move;
import pjatk.edu.pl.pokemon_data.exception.EntityAlreadyExists;
import pjatk.edu.pl.pokemon_data.exception.EntityNotFound;
import pjatk.edu.pl.pokemon_data.repository.MoveRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MoveService extends BaseService<Move> {
    private static final Logger logger = LoggerFactory.getLogger(MoveService.class);
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
        logger.info("Fetching move by API ID: {}", apiId);
        return moveRepository.findByApiId(apiId).orElseThrow(() -> {
            logger.warn("Move with API ID: {} not found.", apiId);
            return new EntityNotFound();
        });
    }

    public Move getMoveByName(String name) {
        logger.info("Fetching move by name: {}", name);
        return moveRepository.findByName(name).orElseThrow(() -> {
            logger.warn("Move with name: {} not found.", name);
            return new EntityNotFound();
        });
    }

    public List<Move> getMoveByAccuracy(Integer accuracy) {
        logger.info("Fetching moves by accuracy: {}", accuracy);
        List<Move> moveList = moveRepository.findByAccuracy(accuracy);
        if (moveList.isEmpty()) {
            logger.warn("No moves found with accuracy: {}", accuracy);
            throw new EntityNotFound();
        } else {
            logger.info("Successfully fetched {} moves with accuracy: {}", moveList.size(), accuracy);
            return moveList;
        }
    }

    public List<Move> getMoveByPower(Integer power) {
        logger.info("Fetching moves by power: {}", power);
        List<Move> moveList = moveRepository.findByPower(power);
        if (moveList.isEmpty()) {
            logger.warn("No moves found with power: {}", power);
            throw new EntityNotFound();
        } else {
            logger.info("Successfully fetched {} moves with power: {}", moveList.size(), power);
            return moveList;
        }
    }

    public List<Move> getMoveByPp(Integer pp) {
        logger.info("Fetching moves by PP: {}", pp);
        List<Move> moveList = moveRepository.findByPp(pp);
        if (moveList.isEmpty()) {
            logger.warn("No moves found with PP: {}", pp);
            throw new EntityNotFound();
        } else {
            logger.info("Successfully fetched {} moves with PP: {}", moveList.size(), pp);
            return moveList;
        }
    }

    private void checkIfMoveAlreadyExists(Move move) {
        logger.info("Checking if move already exists: {}", move);
        Optional<Move> existingMove = moveRepository.findByName(move.getName());
        if (existingMove.isPresent()) {
            logger.warn("Move with name: {} already exists.", move.getName());
            throw new EntityAlreadyExists();
        }
        logger.info("Move with name: {} does not exist. Proceeding to add.", move.getName());
    }
}
//TODO: get all types