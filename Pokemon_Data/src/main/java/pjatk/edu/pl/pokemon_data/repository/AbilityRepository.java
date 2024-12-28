package pjatk.edu.pl.pokemon_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.edu.pl.pokemon_data.entity.Ability;

@Repository
public interface AbilityRepository extends JpaRepository<Ability, Long> {
}
