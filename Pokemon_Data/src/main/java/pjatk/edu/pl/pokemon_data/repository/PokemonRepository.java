package pjatk.edu.pl.pokemon_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT p FROM Pokemon p JOIN p.types t WHERE t.id = :typeId")
    List<Pokemon> findAllByTypeId(@Param("typeId") Long typeId);

    @Query("SELECT p FROM Pokemon p JOIN p.abilities a WHERE a.id = :abilityId")
    List<Pokemon> findAllByAbilityId(@Param("abilityId") Long abilityId);

    @Query("SELECT p FROM Pokemon p JOIN p.moves m WHERE m.id = :moveId")
    List<Pokemon> findAllByMoveId(@Param("moveId") Long moveId);
}
