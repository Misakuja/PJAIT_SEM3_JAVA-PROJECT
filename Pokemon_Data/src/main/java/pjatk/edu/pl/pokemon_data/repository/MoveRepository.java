package pjatk.edu.pl.pokemon_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pjatk.edu.pl.pokemon_data.entity.Move;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoveRepository extends JpaRepository<Move, Long> {
    Optional<Move> findByApiId(Integer id);
    Optional<Move> findByName(String name);
    List<Move> findByAccuracy(Integer accuracy);
    List<Move> findByPower(Integer power);
    List<Move> findByPp(Integer pp);
    @Query("SELECT m FROM Move m JOIN m.type t WHERE t.id = :typeId")
    List<Move> findAllByTypeId(@Param("typeId") Long typeId);
}
