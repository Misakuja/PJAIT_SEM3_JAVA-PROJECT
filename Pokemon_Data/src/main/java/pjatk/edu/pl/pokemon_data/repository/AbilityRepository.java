package pjatk.edu.pl.pokemon_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.edu.pl.pokemon_data.entity.Ability;

import java.util.Optional;

@Repository
public interface AbilityRepository extends JpaRepository<Ability, Long> {
    Optional<Ability> findByApiId(Integer apiId);
    Optional<Ability> findByName(String name);

}
