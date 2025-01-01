package pjatk.edu.pl.pokemon_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.edu.pl.pokemon_data.entity.Pokemon;

import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Optional<Pokemon> findByApiId(Integer id);
    Optional<Pokemon> findByName(String name);
    List<Pokemon> findByBaseExperience(Integer baseExperience);
    List<Pokemon> findByHeight(Integer height);
    List<Pokemon> findByWeight(Integer weight);
}
