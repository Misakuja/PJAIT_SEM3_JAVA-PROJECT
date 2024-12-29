package pjatk.edu.pl.pokemon_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.edu.pl.pokemon_data.entity.Move;

@Repository
public interface MoveRepository extends JpaRepository<Move, Long> {
    Move findByApiId(Integer id);

}
